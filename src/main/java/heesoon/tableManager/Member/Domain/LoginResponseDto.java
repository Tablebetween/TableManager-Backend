package heesoon.tableManager.Member.Domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponseDto {

    private String username;

    private String name;

    private String accessToken;

    @Builder
    private LoginResponseDto(String username, String name, String accessToken) {
        this.username = username;
        this.name = name;
        this.accessToken = accessToken;
    }
}
