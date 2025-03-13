package matt.book.page.content.bookpagecontentpublic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YouTubeVideo implements Serializable {
    private String videoId;
    private String title;
    private String description;
    private String videoUrl;
    private String privacyStatus;
    private List<String> tags;
}
