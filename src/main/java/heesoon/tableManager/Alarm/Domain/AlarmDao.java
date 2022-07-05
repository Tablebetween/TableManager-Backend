package heesoon.tableManager.Alarm.Domain;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AlarmDao {
    private String sendUser;
    private String content;
    private Long statusId;      //게시글인지, 댓글인지의 여부 게시글 : 댓글 1, 좋아요 2 댓글 : 대댓글 3, 좋아요 : 4
    private String receiveUser;

    @Builder
    public AlarmDao(String sendUser, String content, Long statusId, String receiveUser)
    {
        this.sendUser = sendUser;
        this.content = content;
        this.statusId = statusId;
        this.receiveUser = receiveUser;
    }

}
