package heesoon.tableManager.LikeMark.Repository;

import heesoon.tableManager.Board.Domain.Board;
import heesoon.tableManager.LikeMark.Domain.LikeMark;
import heesoon.tableManager.Member.Domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LikeMarkRepository extends JpaRepository<LikeMark,Long> {

    @Query("select l from LikeMark l where l.memberId.memberId =:memberId AND l.boardId.boardId =:boardId AND l.useYn = 'Y'")
    Optional<LikeMark> findByMemberIdAndBoardId(@Param("memberId") Long memberId, @Param("boardId") Long boardId);

}
