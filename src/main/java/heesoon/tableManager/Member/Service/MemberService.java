package heesoon.tableManager.Member.Service;

import heesoon.tableManager.Member.Domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface MemberService extends JpaRepository<Member,Long> {
}
