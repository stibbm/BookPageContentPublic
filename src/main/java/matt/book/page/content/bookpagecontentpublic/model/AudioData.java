package matt.book.page.content.bookpagecontentpublic.model;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AudioData implements Serializable {
    private byte[] audioBytes;
    private String fileType;
}
