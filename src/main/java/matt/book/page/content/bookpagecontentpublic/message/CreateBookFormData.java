package matt.book.page.content.bookpagecontentpublic.message;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookFormData implements Serializable {
	private String bookName;
	private String bookDescription;
	private String bookLanguage;
	private String bookTags;
	private MultipartFile bookThumbnailFile;
}
