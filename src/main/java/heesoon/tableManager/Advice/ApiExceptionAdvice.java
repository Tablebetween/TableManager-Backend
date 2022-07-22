package heesoon.tableManager.Advice;

import heesoon.tableManager.Exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ApiExceptionAdvice {

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<ApiCustomExceptionEntity> customExceptionHandler(final CustomException e) {
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(ApiCustomExceptionEntity.builder()
                        .errorCode(e.getErrorCode().getErrorCode())
                        .errorMessage(e.getErrorCode().getDetail())
                        .build());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiValidationExceptionEntity> validationExceptionHandler(MethodArgumentNotValidException e) {
        log.info("e.getMessage={}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiValidationExceptionEntity.builder()
                        .errorMessage(e.getBindingResult().getFieldError().getDefaultMessage())
                        .build());
    }

}
