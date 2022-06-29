package heesoon.tableManager.Board.Domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BoardDao {
    private Long boardId;
    private String imgurl;
    private String content;
    public BoardDao toDto(Board board){
        return BoardDao.builder().content(board.getContent())
                .boardId(board.getBoardId())
                .imgurl(board.getImg_url())
                .build();
    }

}
