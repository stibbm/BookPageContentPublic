package matt.book.page.content.bookpagecontentpublic.model;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import matt.book.data.client.sqlmodel.chapter.Chapter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookCard implements Serializable {
    private Long bookNumber;
    private String bookName;
    private String createdBy;
    private Long bookViews;
    private List<Chapter> lastTwoChapters;
    private String thumbnailRelativeImageUrl;
}
