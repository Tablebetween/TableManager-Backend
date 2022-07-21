package heesoon.tableManager.LikeMark.Domain;

import heesoon.tableManager.Board.Domain.Board;
import heesoon.tableManager.Board.Domain.Timeentity;
import heesoon.tableManager.Member.Domain.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeMark extends Timeentity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "like_mark_id")
    private Long likeMarkId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board boardId;

    private String useYn;

    @Builder
    public LikeMark(Member memberId, Board boardId, String useYn) {
        this.memberId = memberId;
        this.boardId = boardId;
        this.useYn = useYn;
    }

}
