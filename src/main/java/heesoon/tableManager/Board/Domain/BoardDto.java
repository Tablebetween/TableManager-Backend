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
    private Long memberId;
    private String content;

    public BoardDto toDto(Board board) {
        return BoardDto.builder().content(board.getContent())
                .memberId(board.getMemberId().getMemberId()).build();
    }
}
