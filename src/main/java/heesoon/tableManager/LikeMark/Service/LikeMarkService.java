package heesoon.tableManager.LikeMark.Service;

import org.springframework.stereotype.Component;

@Component
public interface LikeMarkService {

    void processLikeMark(Long memberId, Long boardId);

}
