package heesoon.tableManager.Email.Controller;

import heesoon.tableManager.Email.Service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
public class EmailController {

    private final EmailService emailService;

    @GetMapping("/confirm-email")
    public ResponseEntity<String> viewConfirmEmail(@Valid @RequestParam String token) {
        String result = emailService.verifyEmail(token);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
