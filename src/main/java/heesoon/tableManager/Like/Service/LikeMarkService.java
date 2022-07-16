package heesoon.tableManager.Like.Service;

import heesoon.tableManager.Board.Domain.Board;
import heesoon.tableManager.Member.Domain.Member;
import org.springframework.stereotype.Component;

@Component
public interface LikeMarkService {

    void processLikeMark(Long memberId, Long boardId);

}
