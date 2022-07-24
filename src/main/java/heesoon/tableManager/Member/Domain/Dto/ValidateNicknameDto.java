package heesoon.tableManager.Member.Domain.Dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ValidateNicknameDto {

    @NotBlank(message = "별명을 입력해주세요.")
    private String nickname;
}
