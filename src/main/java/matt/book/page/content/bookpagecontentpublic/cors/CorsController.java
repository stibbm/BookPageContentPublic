package matt.book.page.content.bookpagecontentpublic.cors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CorsController {

	@RequestMapping(value = {
			"createBook",
			"createChapter",
			"createTextBlock",
			"stripeWebhook"
	}, method = RequestMethod.OPTIONS)
	@CrossOrigin("*")
	public @ResponseBody ResponseEntity preflightCorsRequestHandler() {
		try {
			MultiValueMap<String, String> headers = new HttpHeaders();
			headers.add("Access-Control-Allow-Methods", "POST, OPTIONS");
			ResponseEntity response = new ResponseEntity(headers, HttpStatus.OK);
			return response;
		} catch (final RuntimeException runtimeException) {
			ResponseEntity responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("viewAllSimpleStalls preflight cors failure");
			return responseEntity;
		}
	}
}