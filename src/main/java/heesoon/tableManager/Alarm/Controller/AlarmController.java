package heesoon.tableManager.Alarm.Controller;

import heesoon.tableManager.Alarm.Domain.Alarm;
import heesoon.tableManager.Alarm.Domain.AlarmDao;
import heesoon.tableManager.Alarm.Domain.AlarmDto;
import heesoon.tableManager.Alarm.Service.AlarmServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AlarmController {
    private final AlarmServiceImpl alarmService;

    @MessageMapping("/request")
    @SendTo("/topic/board")
    public AlarmDao boradCast(AlarmDto alarmDto) {
        AlarmDao alarmDao = alarmService.makeAlarm(alarmDto);
        return alarmDao;
    }
}
