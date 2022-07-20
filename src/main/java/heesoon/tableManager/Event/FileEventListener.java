package heesoon.tableManager.Event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FileEventListener {
    @EventListener
    public void onFileEventListener(FileEvent fileEvent)
    {
        log.info("event Listener working {}",fileEvent.getEventId());
    }
}
