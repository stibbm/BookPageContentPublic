package matt.book.page.content.bookpagecontentpublic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDetailsCard implements Serializable {
    private Book book;
    private List<Chapter> chapterList;
}
