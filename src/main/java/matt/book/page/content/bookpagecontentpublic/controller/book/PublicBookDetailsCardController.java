package matt.book.page.content.bookpagecontentpublic.controller.book;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import matt.book.data.client.model.PublicBookDetailsCard;
import matt.book.page.content.manager.PublicBookDetailsCardManager;
import matt.book.page.content.model.ApiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PublicBookDetailsCardController {
	private PublicBookDetailsCardManager publicBookDetailsCardManager;

	@Autowired
	public PublicBookDetailsCardController(PublicBookDetailsCardManager publicBookDetailsCardManager) {
		this.publicBookDetailsCardManager = publicBookDetailsCardManager;
	}

	@GetMapping("/getPublicBookDetailsCardByBookNumber")
	@CrossOrigin("*")
	public @ResponseBody ResponseEntity<ApiMessage> getPublicBookDetailsCardByBookNumber(
			@RequestParam String bookNumber,
			@RequestHeader("Authorization") String authToken) throws IOException, InterruptedException {
		log.info("GetPublicBookDetailsCardByBookNumber");
		log.info("Book Number = " + bookNumber);
		PublicBookDetailsCard publicBookDetailsCard = publicBookDetailsCardManager.getPublicBookDetailsCardByBookNumber(
				Long.parseLong(bookNumber), authToken);
		log.info("publicBookDetailsCard: " + publicBookDetailsCard);
		if (publicBookDetailsCard == null) {
			throw new RuntimeException("failed to retrieve PublicBookDetailsCard");
		}
		ApiMessage apiMessage = ApiMessage.builder()
				.body(publicBookDetailsCard)
				.message("successfully retrieved publicBookDetailsCard")
				.error(false)
				.build();
		log.info(apiMessage.toString());
		return ResponseEntity.ok(apiMessage);
	}
}
