package heesoon.tableManager.Email.Service;

import heesoon.tableManager.Email.Domain.EmailToken;
import heesoon.tableManager.Exception.CustomException;
import heesoon.tableManager.Exception.ErrorCode;
import heesoon.tableManager.Member.Domain.Member;
import heesoon.tableManager.Member.Repository.MemberRepository;
import heesoon.tableManager.Member.Service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class EmailService {

    private final EmailTokenService emailTokenService;
    private final MemberRepository memberRepository;

    public String verifyEmail(String token) {
        EmailToken findEmailToken = emailTokenService.findByIdAndExpirationDateAfterAndExpired(token);

        Member findMember = memberRepository.findById(findEmailToken.getMemberId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

       // 이메일 성공 인증 로직 구현현
       findEmailToken.setTokenToUsed();
       findMember.setVerified("Y");
       return "success";

    }

}
