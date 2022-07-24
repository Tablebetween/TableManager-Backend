package heesoon.tableManager.Member.Controller;

import heesoon.tableManager.Member.Domain.Dto.MyProfileDao;
import heesoon.tableManager.Member.Domain.Dto.MyProfilePwDao;
import heesoon.tableManager.Member.Domain.Dto.ProfilePwUpdateDto;
import heesoon.tableManager.Member.Domain.Dto.ProfileUpdateDto;
import heesoon.tableManager.Member.Service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProfileController {

    private final MemberService memberService;

    @GetMapping("/profile/{id}")
    public ResponseEntity<?> showProfile(@PathVariable("id") Long id) {
        MyProfileDao MyProfile = memberService.findMemberProfile(id);
        // MyProfile provider -> studyShare 아니면 변경 input box 비활성화
        return ResponseEntity.ok(MyProfile);
    }

    @GetMapping("/profile/pw/{id}")
    public ResponseEntity<?> showProfilePw(@PathVariable("id") Long id) {
        MyProfilePwDao MyProfilePw = memberService.findMemberProfilePw(id);
        return ResponseEntity.ok(MyProfilePw);
    }

    @PostMapping("/profile/{id}")
    public ResponseEntity<String> updateProfile(@PathVariable("id") Long id, @Valid @RequestBody ProfileUpdateDto profileUpdateDto,
                                                @RequestPart(value = "image", required = false) MultipartFile file) throws IOException {

        memberService.updateProfile(id, profileUpdateDto, file);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PostMapping("/profile/password/{id}")
    public ResponseEntity<String> updateProfilePw(@PathVariable("id") Long id, @Valid @RequestBody ProfilePwUpdateDto profilePwUpdateDto) {

        memberService.updateProfilePw(id, profilePwUpdateDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
