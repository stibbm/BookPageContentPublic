package matt.book.page.content.bookpagecontentpublic.controller.image;

import static matt.book.page.content.constants.Constants.S3_BUCKET;

import java.io.IOException;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;

@Slf4j
@RestController
public class ImageController {
	private ImageManager imageManager;
	private S3ClientWrapper s3ClientWrapper;

	@Autowired
	public ImageController(
			ImageManager imageManager,
			S3ClientWrapper s3ClientWrapper) {
		this.imageManager = imageManager;
		this.s3ClientWrapper = s3ClientWrapper;
	}

	@GetMapping("/getImagesByBookNameAndChapterNumberPaged")
	@CrossOrigin("*")
	public @ResponseBody ResponseEntity<ApiMessage> getImagesByBookNameAndChapterNumberPaged(
			@RequestParam String bookName,
			@RequestParam String chapterNumber,
			@RequestParam String pageNumber,
			@RequestParam String pageSize,
			@RequestHeader("Authorization") String authToken) throws IOException, InterruptedException {
		List<Image> imageList = imageManager.getImagesByBookNameAndChapterNumberPaged(
				bookName,
				chapterNumber,
				pageNumber,
				pageSize,
				authToken);
		ApiMessage apiMessage = ApiMessage.builder()
				.body(imageList)
				.message("successfully retrieved images")
				.error(false)
				.build();
		return ResponseEntity.ok(apiMessage);
	}

	@PostMapping("/createImage")
	@CrossOrigin("*")
	public @ResponseBody ResponseEntity<ApiMessage> createImage(
			@ModelAttribute CreateImageForm createImageForm,
			@RequestHeader("Authorization") String authToken) throws Exception {
		try {
			Image image = imageManager.createImage(
					createImageForm.getBookName(),
					createImageForm.getChapterNumber(),
					createImageForm.getMultipartFile(),
					authToken);
			log.info("imageController created Image: " + image.toString());
			ApiMessage apiMessage = ApiMessage.builder()
					.body(image)
					.message("successfully saved message")
					.error(false)
					.build();
			return ResponseEntity.ok(apiMessage);
		} catch (Exception e) {
			log.info("failed to create image: ");
			log.info(createImageForm.toString());
			return ResponseEntity
					.ok(ApiMessage.builder().body(createImageForm).message(e.getMessage()).error(true).build());
		}
	}

	@GetMapping("/getImage")
	@CrossOrigin("*")
	public ResponseEntity<Resource> getImage(
			@RequestParam String s3Key,
			@RequestParam String s3Bucket) throws IOException {
		byte[] imageBytes = s3ClientWrapper.getS3FileBytes(s3Bucket, s3Key);
		MediaType mediaType = null;
		if (s3Key.endsWith(".png")) {
			mediaType = MediaType.IMAGE_PNG;
		} else if (s3Key.endsWith(".jpeg") || s3Key.endsWith(".jpg")) {
			mediaType = MediaType.IMAGE_JPEG;
		} else {
			throw new IllegalArgumentException();
		}
		ByteArrayResource byteArrayResource = new ByteArrayResource(imageBytes);
		return ResponseEntity.ok().contentType(mediaType).body(byteArrayResource);
	}

}