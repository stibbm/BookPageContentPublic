package matt.book.page.content.bookpagecontentpublic.message;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateChapterWithSpecifiedChapterNumberForm implements Serializable {
	private String bookName;
	private String chapterName;
	private String chapterNumber;
}
