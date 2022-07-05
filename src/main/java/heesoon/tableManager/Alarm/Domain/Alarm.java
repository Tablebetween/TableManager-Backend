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
    private Long sendMemberId;
    private Long contentId;                     //임시 비정규화
    private Long statusId;                      //게시글인지, 댓글인지의 여부 게시글이면 1, 댓글이면 2
    private Long receiveMemberId;

    @Builder
    public Alarm(Long sendMemberId, Long contentId,Long statusId, Long receiveMemberId )
    {
        this.contentId = contentId;
        this.sendMemberId = sendMemberId;
        this.statusId = statusId;
        this.receiveMemberId = receiveMemberId;
    }
}
