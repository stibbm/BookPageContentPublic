package matt.book.page.content.bookpagecontentpublic.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3ClientBean {

    private final AmazonS3Bean amazonS3Bean;

    S3ClientBean(AmazonS3Bean amazonS3Bean) {
        this.amazonS3Bean = amazonS3Bean;
    }
	@Bean
	public S3Client provideS3ClientBean() {
		return S3Client.create();
	}
}
