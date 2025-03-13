package matt.book.page.content.bookpagecontentpublic.service;

import com.google.common.collect.ImmutableList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class YouTubeVideoInfoService {
    public String generateTitle(String bookName, String startChapter, String endChapter) {
        return bookName + " : " + startChapter + "-" + endChapter;
    }

    public String generateDescription(String bookName, String startChapter, String endChapter) {
        return bookName + " : " + startChapter + "-" + endChapter;
    }

    public List<String> generateTags() {
        return ImmutableList.of("webnovel", "audiobook", "manhwa", "manga", "manhua");
    }
}
