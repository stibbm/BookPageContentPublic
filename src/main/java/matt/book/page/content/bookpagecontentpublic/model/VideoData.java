package matt.book.page.content.bookpagecontentpublic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoData implements Serializable {
    private byte[] videoBytes;
    private String videoFileType;
}
