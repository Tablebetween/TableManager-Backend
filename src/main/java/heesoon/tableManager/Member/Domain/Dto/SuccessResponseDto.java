package heesoon.tableManager.Member.Domain.Dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SuccessResponseDto {

    private String message;

    @Builder
    private SuccessResponseDto(String message) {
        this.message = message;
    }
}
