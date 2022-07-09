package heesoon.tableManager.Follow.Domain;

import heesoon.tableManager.Board.Domain.Timeentity;
import heesoon.tableManager.Follow.Domain.Dto.FollowDao;
import heesoon.tableManager.Member.Domain.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="Follow")
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow extends Timeentity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "follow_id")
    private Long followId;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="member_id")
    private Member following;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="member_id")
    private Member follower;

    @Builder
    public Follow(Member following,Member follower)
    {
        this.follower = follower;
        this.following = following;
    }

}
