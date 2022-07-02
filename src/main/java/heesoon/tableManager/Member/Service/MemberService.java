package heesoon.tableManager.Member.Service;

import heesoon.tableManager.Member.Domain.Dto.LoginRequestDto;
import heesoon.tableManager.Member.Domain.Dto.LoginResponseDto;
import heesoon.tableManager.Member.Domain.Dto.SignUpRequestDto;
import org.springframework.stereotype.Component;


@Component
public interface MemberService {
    public LoginResponseDto login(LoginRequestDto loginRequestDto);

    public void signUp(SignUpRequestDto signUpRequestDto);

}
