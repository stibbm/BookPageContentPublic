package matt.book.page.content.bookpagecontentpublic.controller.book;

import java.awt.print.Book;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import matt.book.page.content.bookpagecontentpublic.model.ApiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BookController {
    private BookManager bookManager;
    
    @Autowired
    public BookController(
        BookManager bookManager
    ) {
        this.bookManager = bookManager;
    }
    
    @GetMapping("/getAllBooksPaged")
    @CrossOrigin("*")
    public @ResponseBody ResponseEntity<ApiMessage> getAllBooksPaged(
        @RequestParam String pageNumber,
        @RequestParam String pageSize,
        @RequestHeader("Authorization") String authToken
    ) throws IOException, InterruptedException {
        List<Book> bookList = bookManager.getAllBooksPaged(pageNumber, pageSize, authToken);
        ApiMessage apiMessage = ApiMessage.builder()
            .message("Successfully retrieved books list")
            .body(bookList)
            .error(false)
            .build();
        return ResponseEntity.ok(apiMessage);
    }

	@GetMapping("/getAllBookCardsSortedPaged")
	@CrossOrigin("*")
	public @ResponseBody ResponseEntity<ApiMessage> getAllBookCardsSorted(
		@RequestParam String bookSortType,
		@RequestParam String pageNumber,
		@RequestParam String pageSize,
		@RequestHeader("Authorization") String authToken
	) throws IOException, InterruptedException {
		List<BookCard> bookCardList = bookManager.getAllBookCardsSortedPaged(
			bookSortType,
			pageNumber,
			pageSize,
			authToken
		);
		ApiMessage apiMessage = ApiMessage.builder()
			.body(bookCardList)
			.message("successfully retrieved sorted book cards paged")
			.error(false)
			.build();
		return ResponseEntity.ok(apiMessage);
	}

	@PostMapping("/createBook")
	@CrossOrigin("*")
	public @ResponseBody ResponseEntity<ApiMessage> createBook(
			@ModelAttribute CreateBookFormData createBookFormData,
			@RequestHeader("Authorization") String authToken) throws IOException, InterruptedException {
		Book book = bookManager.createBook(
				createBookFormData.getBookName(),
				createBookFormData.getBookDescription(),
				createBookFormData.getBookLanguage(),
				createBookFormData.getBookTags(),
				createBookFormData.getBookThumbnailFile(),
				authToken);
		ApiMessage apiMessage = ApiMessage.builder()
				.body(book)
				.message("successfully saved book")
				.error(false)
				.build();
		return ResponseEntity.ok(apiMessage);
	}

    
}
