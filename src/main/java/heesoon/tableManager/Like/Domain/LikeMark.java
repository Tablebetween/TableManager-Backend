package heesoon.tableManager.Like.Domain;

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
    @Column(name = "like_id")
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board boardId;

    private String useYn;

    @Builder
    public LikeMark(Member memberId, Board boardId) {
        this.memberId = memberId;
        this.boardId = boardId;
    }

}
