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
public class CreateTextBlockFormData implements Serializable {
	private String bookName;
	private String chapterNumber;
	private String textBlockText;
}
