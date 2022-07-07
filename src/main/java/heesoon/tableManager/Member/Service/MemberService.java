package heesoon.tableManager.Member.Service;

import heesoon.tableManager.Member.Domain.Dto.LoginRequestDto;
import heesoon.tableManager.Member.Domain.Dto.LoginResponseDto;
import heesoon.tableManager.Member.Domain.Dto.MyPageDao;
import heesoon.tableManager.Member.Domain.Dto.SignUpRequestDto;
import heesoon.tableManager.Member.Domain.Member;
import org.springframework.stereotype.Component;


@Component
public interface MemberService {
    public LoginResponseDto login(LoginRequestDto loginRequestDto);
    MyPageDao findUser(Long id);
    public void signUp(SignUpRequestDto signUpRequestDto);

}
