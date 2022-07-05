package heesoon.tableManager.Alarm.Domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AlarmDto {
    private Long sendMemberId;
    private Long contentId;
    private Long statusId;              //게시글인지, 댓글인지의 여부 게시글 : 댓글 1, 좋아요 2 댓글 : 대댓글 3, 좋아요 : 4
    private Long receiveMember;

    public AlarmDto(Long sendMemberId, Long contentId, Long statusId, Long receiveMember)
    {
        this.sendMemberId = sendMemberId;
        this.contentId = contentId;
        this.statusId = statusId;
        this.receiveMember = receiveMember;
    }
}