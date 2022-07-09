package heesoon.tableManager.Follow.Repository;

import heesoon.tableManager.Follow.Domain.Follow;
import heesoon.tableManager.Member.Domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FollowRepository extends JpaRepository<Follow,Long> {
    List<Follow> findByFollowing(Member member);
    List<Follow> findByFollower(Member member);
    Follow findByFollowingAndFollower(Member following, Member follower);
}
