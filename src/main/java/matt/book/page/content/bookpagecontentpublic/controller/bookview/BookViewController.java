package matt.book.page.content.bookpagecontentpublic.controller.bookview;

import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import matt.book.data.client.sqlmodel.bookview.BookView;
import matt.book.page.content.manager.BookViewManager;
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
public class BookViewController {
	private BookViewManager bookViewManager;

	@Autowired
	public BookViewController(BookViewManager bookViewManager) {
		this.bookViewManager = bookViewManager;
	}

	@GetMapping("/createBookView")
	@CrossOrigin("*")
	public @ResponseBody ResponseEntity<ApiMessage> createBookView(
			@RequestParam String bookNumber,
			@RequestHeader("Authorization") String authToken) throws IOException, InterruptedException {
		BookView bookView = bookViewManager.createBookView(bookNumber, authToken);
		List<BookView> bookViewList = bookViewManager.getBookViewsByBookNumber(bookNumber, authToken);
		// log.info(bookViewList);
		// log.info("size = " + bookViewList.size());
		ApiMessage apiMessage = ApiMessage.builder()
				.body(bookView)
				.message("successfully created book view")
				.error(false)
				.build();
		return ResponseEntity.ok(apiMessage);
	}
}
