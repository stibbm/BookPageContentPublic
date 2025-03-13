package matt.book.page.content.bookpagecontentpublic.clientwrapper.pollyclientwrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.polly.PollyClient;
import software.amazon.awssdk.services.polly.model.Engine;
import software.amazon.awssdk.services.polly.model.OutputFormat;
import software.amazon.awssdk.services.polly.model.StartSpeechSynthesisTaskRequest;
import software.amazon.awssdk.services.polly.model.StartSpeechSynthesisTaskResponse;
import software.amazon.awssdk.services.polly.model.VoiceId;


@Slf4j
@Service
public class PollyClientWrapper {
	private VoiceId voiceId;
	private Engine engine;
	private OutputFormat outputFormat;
	private PollyClient pollyClient;

	@Autowired
	public PollyClientWrapper(
		VoiceId voiceId,
		Engine engine,
		OutputFormat outputFormat,
		PollyClient pollyClient
	) {
		this.voiceId = voiceId;
		this.engine = engine;
		this.outputFormat = outputFormat;
		this.pollyClient = pollyClient;
	}

	public void startTextToSpeechTask(String text, String s3BucketName, String s3KeyPrefix) {
		StartSpeechSynthesisTaskRequest request = StartSpeechSynthesisTaskRequest.builder()
			.text(text)
			.voiceId(voiceId)
			.engine(engine)
			.outputFormat(outputFormat)
			.outputS3BucketName(s3BucketName)
			.outputS3KeyPrefix(s3KeyPrefix)
			.build();
		StartSpeechSynthesisTaskResponse response = this.pollyClient
			.startSpeechSynthesisTask(request);
	}
}
