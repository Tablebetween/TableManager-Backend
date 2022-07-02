package heesoon.tableManager.Board.Domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    public BoardDao (Board board) {
        this.boardId = board.getBoardId();
        this.imgurl = board.getImg_url();
        this.content = board.getContent();
        this.use_yn = board.isUse_yn();
        this.updatedAt = board.getUpdatedAt();
    }

    public BoardDao toDto(Board board){
        return BoardDao.builder().content(board.getContent())
                .boardId(board.getBoardId())
                .imgurl(board.getImg_url())
                .build();
    }

}
