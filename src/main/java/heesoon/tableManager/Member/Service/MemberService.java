package heesoon.tableManager.Member.Service;

import heesoon.tableManager.Member.Domain.Dto.*;
import org.springframework.stereotype.Component;


@Component
public interface MemberService {

    LoginResponseDto login(LoginRequestDto loginRequestDto);
    void signUp(SignUpRequestDto signUpRequestDto);
    void validateUsername(ValidateUsernameDto validateUsernameDto);
    void validateEmail(ValidateEmailDto validateEmailDto);
    void validateNickname(ValidateNicknameDto validateNicknameDto);

}
