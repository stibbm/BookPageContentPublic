package matt.book.page.content.bookpagecontentpublic.controller.stripe;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CheckoutController {

	private StripeCheckoutManager stripeCheckoutManager;

	@Autowired
	public CheckoutController(StripeCheckoutManager stripeCheckoutManager) {
		this.stripeCheckoutManager = stripeCheckoutManager;
	}

	@GetMapping("/getStripeCheckoutSessionBySessionId")
	@CrossOrigin("*")
	public @ResponseBody ResponseEntity<ApiMessage> getStripeCheckoutSessionBySessionId(
			@RequestParam String sessionId,
			@RequestHeader("Authorization") String authToken) throws IOException, InterruptedException {
		log.info("getStripeCheckoutSessionBySessionId");
		StripeCheckoutSession stripeCheckoutSession = stripeCheckoutManager.getStripeCheckoutSessionBySessionId(
				sessionId, authToken);
		ApiMessage apiMessage = ApiMessage.builder()
				.body(stripeCheckoutSession)
				.message("successfully got stripeCheckoutSession")
				.error(false)
				.build();
		return ResponseEntity.ok(apiMessage);
	}

	@GetMapping("/getCheckoutSessionBySessionId")
	@CrossOrigin("*")
	public @ResponseBody ResponseEntity<ApiMessage> getCheckoutSessionBySessionId(
			@RequestParam String sessionId,
			@RequestHeader("Authorization") String authToken) throws IOException, InterruptedException {
		log.info("GetCheckoutSessionBySessionId");
		log.info("sessionId: " + sessionId);
		CheckoutSession checkoutSession = stripeCheckoutManager.getCheckoutSessionBySessionId(
				sessionId, authToken);
		ApiMessage apiMessage = ApiMessage.builder()
				.body(checkoutSession)
				.message("successfully retrieved checkout session")
				.error(false)
				.build();
		return ResponseEntity.ok(apiMessage);
	}

	@GetMapping("/createCheckoutPage")
	@CrossOrigin("*")
	public @ResponseBody ResponseEntity<ApiMessage> createCheckoutPage(
			@RequestParam String itemName,
			@RequestParam String baseUrl,
			@RequestHeader("Authorization") String authToken) throws IOException, InterruptedException {
		log.info("CreateCheckoutPage");
		String checkoutPageUrl = stripeCheckoutManager.createCheckoutPage(itemName, baseUrl, authToken);
		log.info("checkoutPageUrl: " + checkoutPageUrl);
		ApiMessage apiMessage = ApiMessage.builder()
				.body(checkoutPageUrl)
				.message("successfully created stripe checkout page")
				.error(false)
				.build();
		return ResponseEntity.ok(apiMessage);
	}
}
