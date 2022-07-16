package heesoon.tableManager.Like.Repository;

import heesoon.tableManager.Board.Domain.Board;
import heesoon.tableManager.Like.Domain.LikeMark;
import heesoon.tableManager.Member.Domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeMarkRepository extends JpaRepository<LikeMark,Long> {

    Optional<LikeMark> findByMemberIdAndBoardId(Member member, Board board);

}
