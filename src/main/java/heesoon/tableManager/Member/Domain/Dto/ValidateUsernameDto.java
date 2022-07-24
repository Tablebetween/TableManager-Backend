package heesoon.tableManager.Member.Domain.Dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ValidateUsernameDto {

    @NotBlank(message = "아이디를 입력해주세요.")
    private String username;
}
