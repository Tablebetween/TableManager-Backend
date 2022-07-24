package heesoon.tableManager.Comment.Service;

import heesoon.tableManager.Comment.Domain.Comment;
import heesoon.tableManager.Comment.Domain.Dto.CommentDto;
import org.springframework.stereotype.Component;

@Component
public interface CommentService {

    Comment createComment(Long boardId, Long memberId, CommentDto commentDto);
}
