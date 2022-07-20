package heesoon.tableManager.Event;


import heesoon.tableManager.Board.Domain.Board;
import heesoon.tableManager.Member.Domain.Member;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class FileEvent {
    private String eventId;
    private Member member;
    private Board board;

    public static FileEvent toCompleteEvent(Member member, Board board)
    {
        return FileEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .member(member).board(board).build();
    }
    public static FileEvent toErrorEvent(Member member, Board board)
    {
        return FileEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .member(member).board(board).build();
    }

}
