package heesoon.tableManager.Comment.Controller;

import heesoon.tableManager.Comment.Domain.Dto.CommentDto;
import heesoon.tableManager.Comment.Service.CommentService;
import heesoon.tableManager.Comment.Service.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentController {

    private CommentService commentService;

    @PostMapping("/comment/{id}")
    public ResponseEntity<String> writeComment(@PathVariable("id") Long boardId, @Valid @RequestBody CommentDto commentDto) {

        Long memberId = 1L;
        commentService.createComment(boardId, memberId, commentDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
