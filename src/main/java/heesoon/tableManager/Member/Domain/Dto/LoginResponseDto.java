package heesoon.tableManager.Member.Domain.Dto;

import heesoon.tableManager.Security.Token;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponseDto {

    private String username;

    private String name;

    private Token accessToken;

    @Builder
    private LoginResponseDto(String username, String name, Token accessToken) {
        this.username = username;
        this.name = name;
        this.accessToken = accessToken;
    }
}
