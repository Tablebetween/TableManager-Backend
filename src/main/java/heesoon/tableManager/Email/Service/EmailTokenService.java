package heesoon.tableManager.Email.Service;

import heesoon.tableManager.Email.Domain.EmailToken;
import heesoon.tableManager.Email.Repository.EmailTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailTokenService {

    private final EmailSenderService emailSenderService;
    private final EmailTokenRepository emailTokenRepository;

    public String createEmailToken(Long memberId, String receiverEmail) {
        Assert.notNull(memberId, "memberId는 필수입니다");
        Assert.hasText(receiverEmail, "receiverEmail은 필수입니다.");

        // 이메일 토큰 저장
        EmailToken emailToken = EmailToken.createEmailToken(memberId);
        emailTokenRepository.save(emailToken);

        // 이메일 전송
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(receiverEmail);
        mailMessage.setSubject("회원가입 이메일 인증");
        mailMessage.setText("http://localhost:8080/confirm-email?token=" + emailToken.getId());
        emailSenderService.sendEmail(mailMessage);

        return emailToken.getId(); // 인증메일 전송 시 토큰 반환
    }

    // 유효한 토큰 가져오기
    public EmailToken findByIdAndExpirationDateAfterAndExpired(String emailTokenId) {

        Optional<EmailToken> emailToken = emailTokenRepository.findByIdAndExpirationDateAfterAndExpired(emailTokenId, LocalDateTime.now(), false);

        //토큰이 없다면 예외 발생
        return emailToken.orElseThrow(() -> new RuntimeException());
    }
}
