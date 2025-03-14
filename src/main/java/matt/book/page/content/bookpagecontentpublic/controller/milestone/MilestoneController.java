package matt.book.page.content.bookpagecontentpublic.controller.milestone;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.css.CSSStyleSheet;

@Slf4j
@RestController
public class MilestoneController {
	private MilestoneManager milestoneManager;

	@Autowired
	public MilestoneController(MilestoneManager milestoneManager) {
		this.milestoneManager = milestoneManager;
	}

	@GetMapping("/updateMilestone")
	@CrossOrigin("*")
	public @ResponseBody ResponseEntity<ApiMessage> updateMilestone(
			@RequestParam VideoCreationMilestone videoCreationMilestone,
			@RequestParam String bookNumber,
			@RequestParam String startChapterNumber,
			@RequestParam String endChapterNumber,
			@RequestHeader("Authorization") String authToken) throws IOException, InterruptedException {
		log.info("update milestone");
		log.info("vid creation time = " + videoCreationMilestone);
		PaymentForTranslatedYoutubeBook paymentForTranslatedYoutubeBook = milestoneManager.updateMilestone(
				videoCreationMilestone, Long.parseLong(bookNumber),
				Long.parseLong(startChapterNumber), Long.parseLong(endChapterNumber), authToken);
		log.info("paymentForTranslatedYoutubeBook = " + paymentForTranslatedYoutubeBook);
		ApiMessage apiMessage = ApiMessage.builder()
				.body(paymentForTranslatedYoutubeBook)
				.message("Milestone updated")
				.error(false)
				.build();
		log.info("apiMessage: " + apiMessage);
		return ResponseEntity.ok(apiMessage);
	}

	@GetMapping("/getMilestone")
	@CrossOrigin("*")
	public @ResponseBody ResponseEntity<ApiMessage> getMilestone(
			@RequestParam String bookNumber,
			@RequestParam String startChapterNumber,
			@RequestParam String endChapterNumber,
			@RequestHeader("Authorization") String authToken) throws IOException, InterruptedException {
		log.info("get milestone");
		log.info("bookNumber: " + bookNumber);
		log.info("startChapterNumber: " + startChapterNumber);
		log.info("endChapterNumber: " + endChapterNumber);
		VideoCreationMilestone videoCreationMilestone = milestoneManager.getMilestone(
				Long.parseLong(bookNumber),
				Long.parseLong(startChapterNumber),
				Long.parseLong(endChapterNumber),
				authToken);
		log.info("videoCreationMilestone");
		log.info(videoCreationMilestone.toString());
		ApiMessage apiMessage = ApiMessage.builder()
				.body(videoCreationMilestone)
				.error(false)
				.message("successfully retrieved milestone")
				.build();
		log.info(apiMessage.toString());
		return ResponseEntity.ok(apiMessage);
	}

}
