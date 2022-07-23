package heesoon.tableManager.Member.Domain.Dto;

import heesoon.tableManager.Member.Domain.Member;
import heesoon.tableManager.Member.Domain.MemberRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequestDto {

    @NotBlank(message = "아이디를 입력해주세요.")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    //@Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*\\W)(?=\\S+$).{6,12}",
    //        message = "비밀번호는 영문자와 숫자, 특수기호가 적어도 1개 이상 포함된 6자~12자의 비밀번호여야 합니다.")
    private String password;

    @NotBlank(message = "별명을 입력해주세요.")
    private String nickname;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "올바른 이메일을 입력해주세요.")
    private String email;

    @NotBlank(message = "생년월일을 입력해주세요.")
    private String birth;

    @NotBlank(message = "성별을 체크해주세요.")
    private String sex;

    @Size(max = 255, message = "한줄 소개는 최대 255자입니다.")
    private String intro;

    public Member toEntity() {
        return Member.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .email(email)
                .birth(birth)
                .sex(sex)
                .intro(intro)
                .role(MemberRole.ROLE_USER)
                .provider("studyShare")
                .build();
    }

}
