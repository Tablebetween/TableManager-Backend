package heesoon.tableManager.Member.Service;

import heesoon.tableManager.AWSS3.S3Service.S3uploader;
import heesoon.tableManager.Exception.CustomException;
import heesoon.tableManager.Exception.ErrorCode;
import heesoon.tableManager.Member.Domain.Dto.LoginRequestDto;
import heesoon.tableManager.Member.Domain.Dto.LoginResponseDto;
import heesoon.tableManager.Member.Domain.Dto.MyPageDao;
import heesoon.tableManager.Member.Domain.Dto.SignUpRequestDto;
import heesoon.tableManager.Member.Domain.Member;
import heesoon.tableManager.Member.Repository.MemberRepository;
import heesoon.tableManager.Security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final S3uploader s3uploader;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Member member = memberRepository.findByUsername(loginRequestDto.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        //첫 번쨰 조건문: 비밀번호 일치 여부
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), member.getPassword())) {
            throw new CustomException(ErrorCode.MISMATCH_PASSWORD);
        }

        return LoginResponseDto.builder()
                .username(member.getUsername())
                .name(member.getName())
                .accessToken(jwtTokenProvider.generateToken(member.getUsername()))
                .build();
    }

    @Override
    public MyPageDao findUser(Long id) {
        Member member =  memberRepository.findById(id).orElse(null);
        int follower = member.getFollowerList().size();
        int following = member.getFollowingList().size();
        MyPageDao myPageDao = MyPageDao.builder()
                .name(member.getNick_name())
                .birth(member.getBirth())
                .pf_url(member.getPf_url())
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

        memberRepository.save(signUpRequestDto.toEntity());

    }

    @Override
    public String loadImage(Long id) {
        Member cMember = memberRepository.findById(id).orElse(null);
        String imagePath = cMember.getPf_url();
        return imagePath;
    }

    @Transactional
    @Override
    public void insertImage(Long id, MultipartFile file) throws IOException {
        String imagePath = s3uploader.upload(file,"static");
        Member cMember = memberRepository.findById(id).orElse(null);
        cMember.setPf_url(imagePath);
    }
}
