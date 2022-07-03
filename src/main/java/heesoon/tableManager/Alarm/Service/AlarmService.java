package heesoon.tableManager.Alarm.Service;

import heesoon.tableManager.Alarm.Domain.AlarmDao;
import heesoon.tableManager.Alarm.Domain.AlarmDto;
import org.springframework.stereotype.Component;

@Component
public interface AlarmService {
    AlarmDao makeAlarm(AlarmDto alarmDto);
}
