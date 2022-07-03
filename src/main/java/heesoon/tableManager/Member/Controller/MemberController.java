package heesoon.tableManager.Member.Controller;

import heesoon.tableManager.Member.Domain.Dto.*;
import heesoon.tableManager.Member.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return new ResponseEntity<>(memberService.login(loginRequestDto), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {
        memberService.signUp(signUpRequestDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PostMapping("/signup/validate/username")
    public ResponseEntity<String> validateUsername(@RequestBody ValidateUsernameDto validateUsernameDto) {
        memberService.validateUsername(validateUsernameDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PostMapping("/signup/validate/email")
    public ResponseEntity<String> validateEmail(@RequestBody ValidateEmailDto validateEmailDto) {
        memberService.validateEmail(validateEmailDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PostMapping("/signup/validate/nickname")
    public ResponseEntity<String> validateNickname(@RequestBody ValidateNicknameDto validateNicknameDto) {
        memberService.validateNickname(validateNicknameDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }




}
