package heesoon.tableManager.Board.Domain;

import heesoon.tableManager.Comment.Domain.Dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BoardDao {
    private Long boardId;
    private String imgurl;
    private String content;
    private boolean use_yn;
    private LocalDateTime updatedAt;
    private List<CommentDto> commentList;

    public BoardDao (Board board) {
        this.boardId = board.getBoardId();
        this.imgurl = board.getImg_url();
        this.content = board.getContent();
        this.use_yn = board.isUse_yn();
        this.updatedAt = board.getUpdatedAt();
        this.commentList = board.getCommentList().stream()
                .filter(comment -> !comment.isUseYn())
                .map(comment -> new CommentDto(comment.getCommentId(), comment.getComment(), comment.isUseYn())) //boolean getter -> get 아닌 is가 prefix
                .collect(Collectors.toList());
    }

    public BoardDao toDto(Board board){
        return BoardDao.builder().content(board.getContent())
                .boardId(board.getBoardId())
                .imgurl(board.getImg_url())
                .build();
    }

}
