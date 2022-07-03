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
    private String receiveUser;

    @Builder
    public AlarmDao(String sendUser, String content,String receiveUser)
    {
        this.sendUser = sendUser;
        this.content = content;
        this.receiveUser = receiveUser;
    }

}
