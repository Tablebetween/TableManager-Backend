package heesoon.tableManager.Member.Controller;

import heesoon.tableManager.Email.Service.EmailService;
import heesoon.tableManager.Email.Service.EmailTokenService;
import heesoon.tableManager.Member.Domain.Dto.*;
import heesoon.tableManager.Member.Service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@RestController
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
    public ResponseEntity<String> validateUsername(@Valid @RequestBody ValidateUsernameDto validateUsernameDto) {
        memberService.validateUsername(validateUsernameDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PostMapping("/signup/validate/email")
    public ResponseEntity<String> validateEmail(@Valid @RequestBody ValidateEmailDto validateEmailDto) {
        memberService.validateEmail(validateEmailDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PostMapping("/signup/validate/nickname")
    public ResponseEntity<String> validateNickname(@Valid @RequestBody ValidateNicknameDto validateNicknameDto) {
        memberService.validateNickname(validateNicknameDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/mypage/{id}")                 //??????????????? ?????????
    public ResponseEntity<?> myInfo(@PathVariable Long id)
    {
        MyPageDao member = memberService.findUser(id);
        return ResponseEntity.ok(member);
    }

    @PostMapping("/mypage/{id}")                // Mypage ????????? ??????
    public ResponseEntity<?> myImage(@PathVariable Long id, @RequestPart(value = "image") MultipartFile file) throws IOException, ParseException
    {
        memberService.insertImage(id,file);
        return new ResponseEntity<>("success",HttpStatus.OK);
    }
    @GetMapping("/mypage/image/{id}")
    public ResponseEntity<?> loadImage(@PathVariable Long id)
    {
        String imagePath = memberService.loadImage(id);
        return new ResponseEntity<>(SuccessResponseDto.builder().message(imagePath).build(),HttpStatus.OK);
    }
}
