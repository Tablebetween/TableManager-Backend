package heesoon.tableManager.Alarm.Domain;

import heesoon.tableManager.Board.Domain.Timeentity;
import heesoon.tableManager.Member.Domain.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Alarm")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Alarm extends Timeentity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "alarm_id")
    private Long alarmId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member sendMemberId;
    private String content;
    private Long statusId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member receiveMemberId;

    @Builder
    public Alarm(Member sendMemberId, String content,Long statusId, Member receiveMemberId )
    {
        this.content = content;
        this.sendMemberId = sendMemberId;
        this.statusId = statusId;
        this.receiveMemberId = receiveMemberId;
    }
}
