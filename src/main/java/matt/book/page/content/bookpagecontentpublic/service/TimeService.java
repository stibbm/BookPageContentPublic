package matt.book.page.content.bookpagecontentpublic.service;

import java.time.Instant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TimeService {
    public Long getCurrentTimestampEpochMilli() {
        Long currentTimestamp = Instant.now().toEpochMilli();
        return currentTimestamp;
    }
}
