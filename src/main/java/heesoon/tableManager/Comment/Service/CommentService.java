package heesoon.tableManager.Comment.Service;

import heesoon.tableManager.Comment.Domain.Comment;
import heesoon.tableManager.Comment.Domain.Dto.CommentDto;
import heesoon.tableManager.Comment.Domain.Dto.UpdateCommentDto;
import org.springframework.stereotype.Component;

@Component
public interface CommentService {

    Comment createComment(Long boardId, Long memberId, CommentDto commentDto);
    void deleteComment(Long boardId, Long commentId);
    void updateComment(Long commentId, UpdateCommentDto updateCommentDto);
}
