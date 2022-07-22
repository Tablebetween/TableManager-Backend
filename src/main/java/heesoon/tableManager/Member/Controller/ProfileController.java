package heesoon.tableManager.Member.Controller;

import heesoon.tableManager.Member.Domain.Dto.MyProfileDao;
import heesoon.tableManager.Member.Domain.Dto.ProfileUpdateDto;
import heesoon.tableManager.Member.Service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProfileController {

    private final MemberService memberService;

    @GetMapping("/profile/{id}")
    public ResponseEntity<?> showProfile(@PathVariable("id") Long id) {
        MyProfileDao MyProfile = memberService.findMember(id);
        return ResponseEntity.ok(MyProfile);
    }

    @PostMapping("/profile/{id}")
    public ResponseEntity<String> updateProfile(@PathVariable("id") Long id, @Valid @RequestBody ProfileUpdateDto profileUpdateDto,
                                                @RequestPart(value = "image", required = false) MultipartFile file) {

        memberService.updateProfile(id, profileUpdateDto, file);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
