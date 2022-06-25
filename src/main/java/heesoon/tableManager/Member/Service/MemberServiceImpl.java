package heesoon.tableManager.Member.Service;

import heesoon.tableManager.Exception.CustomException;
import heesoon.tableManager.Exception.ErrorCode;
import heesoon.tableManager.Member.Domain.LoginRequestDto;
import heesoon.tableManager.Member.Domain.LoginResponseDto;
import heesoon.tableManager.Member.Domain.Member;
import heesoon.tableManager.Member.Repository.MemberRepository;
import heesoon.tableManager.Security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Member member = memberRepository.findByUsername(loginRequestDto.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        //첫 번쨰 조건문: 비밀번호 일치 여부
        if (!loginRequestDto.getPassword().equals(member.getPassword())) {
            throw new CustomException(ErrorCode.MISMATCH_PASSWORD);
        }

        return LoginResponseDto.builder()
                .username(member.getUsername())
                .name(member.getName())
                .accessToken(jwtTokenProvider.generateToken(member.getUsername()))
                .build();
    }
}
