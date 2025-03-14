package matt.book.page.content.bookpagecontentpublic.controller.chapter;

import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import matt.book.data.client.sqlmodel.chapter.Chapter;
import matt.book.page.content.manager.ChapterManager;
import matt.book.page.content.message.CreateChapterForm;
import matt.book.page.content.message.CreateChapterWithSpecifiedChapterNumberForm;
import matt.book.page.content.model.ApiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ChapterController {

	private ChapterManager chapterManager;

	@Autowired
	public ChapterController(ChapterManager chapterManager) {
		this.chapterManager = chapterManager;
	}

	@PostMapping("/createChapterWithSpecifiedChapterNumber")
	@CrossOrigin("*")
	public @ResponseBody ResponseEntity<ApiMessage> createChapterWithSpecifiedChapterNumber(
			@ModelAttribute CreateChapterWithSpecifiedChapterNumberForm createChapterWithSpecifiedChapterNumberForm,
			@RequestHeader("Authorization") String authToken) throws IOException, InterruptedException {
		Chapter chapter = chapterManager.createChapterWithSpecifiedChapterNumber(
				createChapterWithSpecifiedChapterNumberForm.getBookName(),
				createChapterWithSpecifiedChapterNumberForm.getChapterName(),
				Long.parseLong(createChapterWithSpecifiedChapterNumberForm.getChapterNumber()),
				authToken);
		if (chapter == null) {
			throw new RuntimeException("Failed to create chapter");
		}
		ApiMessage apiMessage = ApiMessage.builder()
				.error(false)
				.body(chapter)
				.message("successfully created chapter")
				.build();
		return ResponseEntity.ok(apiMessage);
	}

	@PostMapping("/createChapter")
	@CrossOrigin("*")
	public @ResponseBody ResponseEntity<ApiMessage> createChapter(
			@ModelAttribute CreateChapterForm createChapterForm,
			@RequestHeader("Authorization") String authToken) throws IOException, InterruptedException {

		Chapter chapter = chapterManager.createChapter(
				createChapterForm.getBookName(),
				createChapterForm.getChapterName(),
				authToken);
		ApiMessage apiMessage = ApiMessage.builder()
				.body(chapter)
				.message("success")
				.error(false)
				.build();
		return ResponseEntity.ok(apiMessage);
	}

}
