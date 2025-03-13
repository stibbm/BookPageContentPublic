package matt.book.page.content.bookpagecontentpublic.bean;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.theokanning.openai.service.OpenAiService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class OpenAiServiceBean {
	private final String openAIToken = "<openAIToken>";
	private final Duration timeout = Duration.ofSeconds(300);

	@Bean
	public OpenAiService provideOpenAiServiceBean() {
		OpenAiService openAiService = new OpenAiService(openAIToken, timeout);
		return openAiService;
	}
}
