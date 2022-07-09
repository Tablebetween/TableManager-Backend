package heesoon.tableManager.Alarm.Domain;

import heesoon.tableManager.Board.Domain.Board;
import heesoon.tableManager.Board.Domain.Timeentity;
import heesoon.tableManager.Comment.Domain.Comment;
import heesoon.tableManager.Follow.Domain.Follow;
import heesoon.tableManager.Member.Domain.Member;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "Alarm")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Alarm extends Timeentity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "alarm_id")
    private Long alarmId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member sendMemberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id")
    private Board boardId;

    private Long statusId;                      //게시글인지, 댓글인지의 여부 게시글이면 1, 댓글이면 2

    @ManyToOne(fetch = FetchType.LAZY)
    private Member receiveMemberId;

    @Builder
    public Alarm(Member sendMemberId, Board board,Long statusId, Member receiveMemberId )
    {
        this.boardId = board;
        this.sendMemberId = sendMemberId;
        this.statusId = statusId;
        this.receiveMemberId = receiveMemberId;
    }
}
