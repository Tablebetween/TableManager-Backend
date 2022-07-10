package heesoon.tableManager.Security;

import heesoon.tableManager.Exception.CustomException;
import heesoon.tableManager.Exception.ErrorCode;
import heesoon.tableManager.Member.Domain.Member;
import heesoon.tableManager.Member.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("userDetails 들어오나???");
        log.info("username = {}", username);

        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.MISMATCH_ACCESS_TOKEN));

        return new PrincipalDetails(member);
    }
}
