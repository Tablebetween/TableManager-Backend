package heesoon.tableManager.Alarm.Repository;


import heesoon.tableManager.Alarm.Domain.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Repository;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Long> {
}
