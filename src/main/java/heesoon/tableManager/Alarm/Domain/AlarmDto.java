package heesoon.tableManager.Alarm.Domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AlarmDto {
    private Long sendMemberId;
    private String content;
    private Long statusId;              //게시글인지, 댓글인지의 여부
    private Long receiveMember;

    public AlarmDto(Long sendMemberId, String content, Long statusId, Long receiveMember)
    {
        this.sendMemberId = sendMemberId;
        this.content = content;
        this.statusId = statusId;
        this.receiveMember = receiveMember;
    }
}