package matt.book.page.content.bookpagecontentpublic.controller.stripe;

import com.stripe.exception.StripeException;
import java.io.IOException;
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
public class GetItemByCheckoutSessionIdActivity {
	private StripeManager stripeManager;
	private StripeCheckoutManager stripeCheckoutManager;

	@Autowired
	public GetItemByCheckoutSessionIdActivity(
			StripeManager stripeManager,
			StripeCheckoutManager stripeCheckoutManager) {
		this.stripeManager = stripeManager;
		this.stripeCheckoutManager = stripeCheckoutManager;
	}

	@GetMapping("/getItemByCheckoutSessionId")
	@CrossOrigin("*")
	public @ResponseBody ResponseEntity<ApiMessage> getItemByCheckoutSessionId(
			@RequestParam String checkoutSessionId,
			@RequestHeader("Authorization") String authToken)
			throws IOException, InterruptedException, StripeException {
		CheckoutSession checkoutSession = stripeCheckoutManager.getCheckoutSessionBySessionId(checkoutSessionId,
				authToken);
		StripeCheckoutSession stripeCheckoutSession = stripeCheckoutManager
				.getStripeCheckoutSessionBySessionId(checkoutSessionId, authToken);
		String priceId = checkoutSession.getPriceId();
		Item item = stripeManager.getItemPriceByPriceId(priceId);
		ApiMessage apiMessage = ApiMessage.builder()
				.message("successfully retrieved item")
				.body(item)
				.error(false)
				.build();
		return ResponseEntity.ok(apiMessage);
	}

}
