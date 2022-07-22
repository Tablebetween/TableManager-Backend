package heesoon.tableManager.Advice;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ApiValidationExceptionEntity {

    private String errorMessage;

    @Builder
    public ApiValidationExceptionEntity (String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
