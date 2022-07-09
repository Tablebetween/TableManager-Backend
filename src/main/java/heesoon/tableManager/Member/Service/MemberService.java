package heesoon.tableManager.Member.Service;

import heesoon.tableManager.Member.Domain.Dto.LoginRequestDto;
import heesoon.tableManager.Member.Domain.Dto.LoginResponseDto;
import heesoon.tableManager.Member.Domain.Dto.SignUpRequestDto;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Component
public interface MemberService {

    LoginResponseDto login(LoginRequestDto loginRequestDto);
    void signUp(SignUpRequestDto signUpRequestDto);
    void validateUsername(ValidateUsernameDto validateUsernameDto);
    void validateEmail(ValidateEmailDto validateEmailDto);
    void validateNickname(ValidateNicknameDto validateNicknameDto);
    public LoginResponseDto login(LoginRequestDto loginRequestDto);
    MyPageDao findUser(Long id);
    public void signUp(SignUpRequestDto signUpRequestDto);
    String loadImage(Long id);
    void insertImage(Long id, MultipartFile file) throws IOException;

}
