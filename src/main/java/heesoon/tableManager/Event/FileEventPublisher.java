package heesoon.tableManager.Event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FileEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public void notifyComplete(FileEvent fileEvent)
    {
        eventPublisher.publishEvent(fileEvent);
    }
    public void notifyError(FileEvent fileEvent)
    {
        eventPublisher.publishEvent(fileEvent);
    }
}
