package matt.book.page.content.bookpagecontentpublic.controller.chapter;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import matt.book.data.client.model.ChapterHeader;
import matt.book.page.content.manager.ChapterHeaderManager;
import matt.book.page.content.model.ApiMessage;
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
public class ChapterHeaderController {

	public static final String GET_CHAPTER_HEADERS_BY_BOOK_NAME_ROUTE = "/getChapterHeadersByBookName";
	private ChapterHeaderManager chapterHeaderManager;

	@Autowired
	public ChapterHeaderController(ChapterHeaderManager chapterHeaderManager) {
		this.chapterHeaderManager = chapterHeaderManager;
	}

	@GetMapping(GET_CHAPTER_HEADERS_BY_BOOK_NAME_ROUTE)
	@CrossOrigin("*")
	public @ResponseBody ResponseEntity<ApiMessage> getChapterHeadersByBookName(
			@RequestParam String bookName,
			@RequestHeader("Authorization") String authToken) throws IOException, InterruptedException {
		Long startTime = Instant.now().getEpochSecond();
		log.info(GET_CHAPTER_HEADERS_BY_BOOK_NAME_ROUTE + " called");
		List<ChapterHeader> chapterHeaderList = chapterHeaderManager.getChapterHeadersByBookName(
				bookName,
				authToken);
		ApiMessage apiMessage = new ApiMessage(chapterHeaderList,
				"successfully retrieved chapterHeadersByBookName");
		log.info(
				"Successfully retrieved chapterHeaders size = : " + chapterHeaderList.size());
		log.info("bookName=" + bookName);
		Long endTime = Instant.now().getEpochSecond();
		Long timeInSeconds = endTime - startTime;
		log.info("runtime in seconds for getChapterHeadersByBookName = " + timeInSeconds + " seconds");
		return ResponseEntity.ok(apiMessage);
	}

}
