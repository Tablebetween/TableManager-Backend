package heesoon.tableManager.Board.Domain;

import heesoon.tableManager.Member.Domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BoardDto {
    private Long member_id;
    private String content;

    public BoardDto toDto(Board board) {
        return BoardDto.builder().content(board.getContent())
                .member_id(board.getMember_id().getMember_id()).build();
    }
}
