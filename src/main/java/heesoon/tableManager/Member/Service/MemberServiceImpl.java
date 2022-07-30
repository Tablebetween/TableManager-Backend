package heesoon.tableManager.Member.Service;

import heesoon.tableManager.AWSS3.S3Service.S3uploader;
import heesoon.tableManager.Email.Service.EmailTokenService;
import heesoon.tableManager.Exception.CustomException;
import heesoon.tableManager.Exception.ErrorCode;
import heesoon.tableManager.Member.Domain.Dto.*;
import heesoon.tableManager.Member.Domain.Dto.LoginRequestDto;
import heesoon.tableManager.Member.Domain.Dto.LoginResponseDto;
import heesoon.tableManager.Member.Domain.Dto.MyPageDao;
import heesoon.tableManager.Member.Domain.Dto.SignUpRequestDto;
import heesoon.tableManager.Member.Domain.Member;
import heesoon.tableManager.Member.Repository.MemberRepository;
import heesoon.tableManager.Security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final S3uploader s3uploader;
    private final JwtTokenProvider jwtTokenProvider;
    private final EmailTokenService emailTokenService;

    @Transactional
    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Member member = memberRepository.findByUsername(loginRequestDto.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));


        //첫 번째 조건문: 이메일인증 여부
        if (!member.getVerified().equals("Y")) {
            throw new CustomException(ErrorCode.NOT_CERTIFIED_EMAIL);
        }

        //두 번쨰 조건문: 비밀번호 일치 여부
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), member.getPassword())) {
            throw new CustomException(ErrorCode.MISMATCH_PASSWORD);
        }


        return LoginResponseDto.builder()
                .username(member.getUsername())
                .name(member.getName())
                .accessToken(jwtTokenProvider.generateToken(member.getMemberId(), member.getUsername()))
                .build();
    }

    @Override
    public MyPageDao findUser(Long id) {
        Member member =  memberRepository.findById(id).orElse(null);
        int follower = member.getFollowerList().size();
        int following = member.getFollowingList().size();
        MyPageDao myPageDao = MyPageDao.builder()
                .name(member.getNickname())
                .birth(member.getBirth())
                .pf_url(member.getPfUrl())
                .follower(following)
                .following(follower)
                .intro(member.getIntro()).build();
        return myPageDao;

    }

    @Transactional
    @Override
    public void signUp(SignUpRequestDto signUpRequestDto) {
        String username = signUpRequestDto.getUsername();
        String encPassword = passwordEncoder.encode(signUpRequestDto.getPassword());
        String email = signUpRequestDto.getEmail();

        if (memberRepository.existsByUsername(username)) {
            throw new CustomException(ErrorCode.DUPLICATE_USERNAME);
        }
        if (memberRepository.existsByEmail(email)) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }

        signUpRequestDto.setPassword(encPassword);

        // 멤버생성 및 이메일 인증
        Member createMember = memberRepository.save(signUpRequestDto.toEntity());
        emailTokenService.createEmailToken(createMember.getMemberId(), createMember.getEmail());
    }

    @Override
    public void validateUsername(ValidateUsernameDto validateUsernameDto) {
        String username = validateUsernameDto.getUsername();

        if (memberRepository.existsByUsername(username)) {
            throw new CustomException(ErrorCode.DUPLICATE_USERNAME);
        }
    }

    @Override
    public void validateEmail(ValidateEmailDto validateEmailDto) {
        String email = validateEmailDto.getEmail();

        if (memberRepository.existsByUsername(email)) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }
    }

    @Override
    public void validateNickname(ValidateNicknameDto validateNicknameDto) {
        String nickname = validateNicknameDto.getNickname();

        if (memberRepository.existsByNickname(nickname)) {
            throw new CustomException(ErrorCode.DUPLICATE_NICKNAME);
        }
    }
    @Override
    public String loadImage(Long id) {
        Member cMember = memberRepository.findById(id).orElse(null);
        String imagePath = cMember.getPfUrl();
        return imagePath;
    }

    @Transactional
    @Override
    public void insertImage(Long id, MultipartFile file) throws IOException {
        String imagePath = s3uploader.upload(file,"static");
        Member cMember = memberRepository.findById(id).orElse(null);
        cMember.setPfUrl(imagePath);
    }

    //설정 > 프로필

    @Override
    public MyProfileDao findMemberProfile(Long id) {
        Member findMember = memberRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return MyProfileDao.builder()
                .nickname(findMember.getNickname())
                .birth(findMember.getBirth())
                .email(findMember.getEmail())
                .intro(findMember.getIntro())
                .pfUrl(findMember.getPfUrl())
                .provider(findMember.getProvider())
                .build();
    }

    @Override
    public MyProfilePwDao findMemberProfilePw(Long id) {
        Member findMember = memberRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return MyProfilePwDao.builder()
                .password(findMember.getPassword())
                .build();
    }

    /**
     * 일반로그인, oauth로그인 분기처리 해야함.
     * 프로필 이미지 S3업로드+DB 이미지정보 입력(원자적)
     */
    @Transactional
    @Override
    public void updateProfile(Long id, ProfileUpdateDto profileUpdateDto, MultipartFile file) throws IOException {
        Member findMember = memberRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        findMember.setName(profileUpdateDto.getNickname());
        findMember.setBirth(profileUpdateDto.getBirth());
        findMember.setEmail(profileUpdateDto.getEmail());
        findMember.setIntro(profileUpdateDto.getIntro());

        String imagePath = s3uploader.upload(file, "static");
        findMember.setPfUrl(imagePath);
    }

    @Transactional
    @Override
    public void updateProfilePw(Long id, ProfilePwUpdateDto profilePwUpdateDto) {
        Member findMember = memberRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        findMember.setPassword(passwordEncoder.encode(profilePwUpdateDto.getPassword()));
    }
}
