package heesoon.tableManager.Advice;

import lombok.Builder;
import lombok.Getter;
import org.apache.http.HttpStatus;

@Getter
public class ApiCustomExceptionEntity {

    private String errorCode;
    private String errorMessage;

    @Builder
    public ApiCustomExceptionEntity(HttpStatus status, String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
