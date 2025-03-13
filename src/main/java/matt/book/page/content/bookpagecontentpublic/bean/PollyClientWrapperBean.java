package matt.book.page.content.bookpagecontentpublic.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.services.polly.PollyClient;
import software.amazon.awssdk.services.polly.model.Engine;
import software.amazon.awssdk.services.polly.model.OutputFormat;
import software.amazon.awssdk.services.polly.model.VoiceId;

@Configuration
public class PollyClientWrapperBean {

    private final AmazonS3Bean amazonS3Bean;
	private final VoiceId VOICE_ID = VoiceId.MATTHEW;
	private final Engine ENGINE = Engine.NEURAL;
	private final OutputFormat OUTPUT_FORMAT = OutputFormat.MP3;

    PollyClientWrapperBean(AmazonS3Bean amazonS3Bean) {
        this.amazonS3Bean = amazonS3Bean;
    }

	@Bean
	public VoiceId provideVoiceIdBean() {
		return VOICE_ID;
	}

	@Bean
	public Engine provideEngineBean() {
		return ENGINE;
	}

	@Bean
	public OutputFormat provideOutputFormat() {
		return OUTPUT_FORMAT;
	}

	@Bean
	public PollyClient providePollyClientBean() {
		return PollyClient.create();
	}
}