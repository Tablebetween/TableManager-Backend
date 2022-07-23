package heesoon.tableManager.Email.Domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailToken {

    private static final long EMAIL_TOKEN_EXPIRATION_TIME_VALUE = 5L; //이메일 토큰 만료시간

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    private LocalDateTime expirationDate;

    private boolean expired;

    private Long memberId;

    public static EmailToken createEmailToken(Long memberId) {
        EmailToken emailToken = new EmailToken();
        emailToken.expirationDate = LocalDateTime.now().plusMinutes(EMAIL_TOKEN_EXPIRATION_TIME_VALUE); //5분후 만료
        emailToken.expired = false;
        emailToken.memberId = memberId;

        return emailToken;
    }

    public void setTokenToUsed() {
        this.expired = true;
    }
}
