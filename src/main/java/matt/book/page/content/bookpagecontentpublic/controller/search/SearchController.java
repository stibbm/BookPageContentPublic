package matt.book.page.content.bookpagecontentpublic.controller.search;

import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
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
public class SearchController {

	private SearchManager searchManager;

	@Autowired
	public SearchController(SearchManager searchManager) {
		this.searchManager = searchManager;
	}

	@GetMapping("/searchBooksByBookTags")
	@CrossOrigin("*")
	public @ResponseBody ResponseEntity<ApiMessage> searchBooksByBookTags(
			@RequestParam List<String> bookTags,
			@RequestParam TagFilterType tagFilterType,
			@RequestHeader("Authorization") String authToken) throws IOException, InterruptedException {
		List<Book> bookList = searchManager.searchBooksByBookTags(bookTags, tagFilterType,
				authToken);
		ApiMessage apiMessage = ApiMessage.builder()
				.body(bookList)
				.message("successfully searched books by book tags")
				.error(false)
				.build();
		return ResponseEntity.ok(apiMessage);
	}

	@GetMapping("/searchBooksByContent")
	@CrossOrigin("*")
	public @ResponseBody ResponseEntity<ApiMessage> searchBooksByContent(
			@RequestParam String searchInput,
			@RequestParam SearchTermFilterType searchTermFilterType,
			@RequestHeader("Authorization") String authToken) throws IOException, InterruptedException {
		List<Book> bookList = searchManager.searchBooksByContent(searchInput, searchTermFilterType,
				authToken);
		ApiMessage apiMessage = ApiMessage.builder()
				.body(bookList)
				.message("successfully retrieved books by content")
				.error(false)
				.build();
		return ResponseEntity.ok(apiMessage);
	}

	@GetMapping("/searchBookCardsByBookTags")
	@CrossOrigin("*")
	public @ResponseBody ResponseEntity<ApiMessage> searchBookCardsByBookTags(
			@RequestParam List<String> bookTags,
			@RequestParam TagFilterType tagFilterType,
			@RequestHeader("Authorization") String authToken) throws IOException, InterruptedException {
		log.info("search books by book tags");
		log.info("bookTags: " + bookTags);
		log.info("tagFilterType: " + tagFilterType);
		List<BookCard> bookCardList = searchManager.searchBookCardsByBookTags(bookTags, tagFilterType, authToken);
		ApiMessage apiMessage = ApiMessage.builder().body(bookCardList).message("successfully retrieved bookCardList")
				.error(false)
				.build();
		return ResponseEntity.ok(apiMessage);
	}

	@GetMapping("/searchBookCardsByContent")
	@CrossOrigin("*")
	public @ResponseBody ResponseEntity<ApiMessage> searchBookCardsByContent(
			@RequestParam String searchInput,
			@RequestParam SearchTermFilterType searchTermFilterType,
			@RequestHeader("Authorization") String authToken) throws IOException, InterruptedException {
		List<BookCard> bookCardList = searchManager.searchBookCardsByContent(searchInput, searchTermFilterType,
				authToken);
		ApiMessage apiMessage = ApiMessage.builder().body(bookCardList).message("successfully retrieved bookCardList")
				.error(false)
				.build();
		return ResponseEntity.ok(apiMessage);
	}
}
