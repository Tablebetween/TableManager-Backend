package heesoon.tableManager.Member.Service;

import heesoon.tableManager.Member.Domain.LoginRequestDto;
import heesoon.tableManager.Member.Domain.LoginResponseDto;
import heesoon.tableManager.Member.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public interface MemberService {
    public LoginResponseDto login(LoginRequestDto loginRequestDto);

}
