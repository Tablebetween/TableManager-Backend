package heesoon.tableManager.Comment.Domain;

import heesoon.tableManager.Board.Domain.Board;
import heesoon.tableManager.Board.Domain.Timeentity;
import heesoon.tableManager.Member.Domain.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Comment")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends Timeentity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private Long commentId;
    private String comment;
    private boolean useYn;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board boardId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member memberId;


}
