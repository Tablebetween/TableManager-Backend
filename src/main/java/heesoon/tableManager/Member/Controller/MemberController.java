package heesoon.tableManager.Member.Controller;

import heesoon.tableManager.Member.Domain.Dto.MyPageDao;
import heesoon.tableManager.Member.Domain.Dto.SignUpRequestDto;
import heesoon.tableManager.Member.Domain.Dto.SuccessResponseDto;
import heesoon.tableManager.Member.Domain.Member;
import heesoon.tableManager.Member.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<SuccessResponseDto> signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {
        memberService.signUp(signUpRequestDto);
        return new ResponseEntity<>(SuccessResponseDto.builder().message("회원가입 완료").build(), HttpStatus.OK);
    }

    @GetMapping("/mypage/{id}")
    public ResponseEntity<?> myInfo(@PathVariable Long id)
    {
        MyPageDao member = memberService.findUser(id);
        return ResponseEntity.ok(member);
    }

}
