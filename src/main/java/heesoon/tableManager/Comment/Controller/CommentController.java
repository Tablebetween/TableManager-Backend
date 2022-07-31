package heesoon.tableManager.Comment.Controller;

import heesoon.tableManager.Comment.Domain.Dto.CommentDto;
import heesoon.tableManager.Comment.Domain.Dto.UpdateCommentDto;
import heesoon.tableManager.Comment.Service.CommentService;
import heesoon.tableManager.Security.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/board/{boardId}/comment")
    public ResponseEntity<String> writeComment(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable("boardId") Long boardId,
                                               @Valid @RequestBody CommentDto commentDto) {

        Long memberId = principalDetails.getMember().getMemberId();
        commentService.createComment(boardId, memberId, commentDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PutMapping("/board/{boardId}/comment/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable("boardId") Long boardId, @PathVariable("commentId") Long commentId, @Valid @RequestBody UpdateCommentDto updateCommentDto) {
        commentService.updateComment(commentId, updateCommentDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @DeleteMapping("/board/{boardId}/comment/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable("boardId") Long boardId, @PathVariable("commentId") Long commentId) {
        commentService.deleteComment(boardId, commentId);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
