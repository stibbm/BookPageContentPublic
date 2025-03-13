package matt.book.page.content.bookpagecontentpublic.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AmazonS3Bean {
	@Bean
	public AmazonS3 provideAmazonS3Bean() {
		return AmazonS3ClientBuilder.defaultClient();
	}
}