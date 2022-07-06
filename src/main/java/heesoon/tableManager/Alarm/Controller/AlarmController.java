package heesoon.tableManager.Alarm.Controller;

import heesoon.tableManager.Alarm.Domain.AlarmDto;
import heesoon.tableManager.Alarm.Service.AlarmServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AlarmController {

    private final AlarmServiceImpl alarmService;
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/board/{id}")
    public void boradCast(@Payload AlarmDto alarmDto, @DestinationVariable Integer id) {
        Object alarmDao = alarmService.makeAlarm(alarmDto);
        this.simpMessagingTemplate.convertAndSend("/queue/addAlarmToClient/"+id,alarmDao);
    }
}
