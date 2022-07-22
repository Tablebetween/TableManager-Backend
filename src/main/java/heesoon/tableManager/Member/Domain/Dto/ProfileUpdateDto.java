package heesoon.tableManager.Member.Domain.Dto;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class ProfileUpdateDto {

    @NotBlank(message = "사용자 이름을 입력해주세요.")
    private String nickname;

    @NotBlank(message = "생년월일을 입력해주세요.")
    private String birth;

    @Email(message = "올바른 이메일 형식이 아닙니다.")
    @NotBlank(message = "이메일주소를 입력해주세요.")
    private String email;

    @Size(max = 255, message = "최대 255자 입니다.")
    private String intro;

}
