package heesoon.tableManager.Member.Service;

import heesoon.tableManager.Member.Domain.Dto.*;
import heesoon.tableManager.Member.Domain.Member;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Component
public interface MemberService {

    // 로그인
    LoginResponseDto login(LoginRequestDto loginRequestDto);
    void signUp(SignUpRequestDto signUpRequestDto);
    void validateUsername(ValidateUsernameDto validateUsernameDto);
    void validateEmail(ValidateEmailDto validateEmailDto);
    void validateNickname(ValidateNicknameDto validateNicknameDto);

    MyPageDao findUser(Long id);
    String loadImage(Long id);
    void insertImage(Long id, MultipartFile file) throws IOException;

    //설정 > 프로필 수정
    MyProfileDao findMemberProfile(Long id);
    MyProfilePwDao findMemberProfilePw(Long id);
    void updateProfile(Long id, ProfileUpdateDto profileUpdateDto, MultipartFile file) throws IOException;
    void updateProfilePw(Long id, ProfilePwUpdateDto profilePwUpdateDto);

}
