package matt.book.page.content.bookpagecontentpublic.bean;

import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.annotation.Bean;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.youtube.YouTube;

public class YoutubeServiceBean {

	private Credential authorize(final NetHttpTransport httpTransport) throws IOException {
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
			JSON_FACTORY, new InputStreamReader(new FileInputStream(CLIENT_SECRETS))
		);
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
			httpTransport, JSON_FACTORY, clientSecrets, SCOPES
		)
		.setDataStoreFactory(new FileDataStoreFactory(DATA_STORE_DIR))
		.setAccessType("offline")
		.build();
		Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user").setExpiresInSeconds(SECONDS_IN_7_DAYS);
		return credential;
	}

	@Bean
	public YouTube provideYoutubeServiceBean() throws IOException {
		final NetHttpTransport httpTransport = new NetHttpTransport();
		Credential credential = authorize(httpTransport);
		return new YouTube.Builder(httpTransport, JSON_FACTORY, credential)
		.setApplicationName("applicationNameHere")
		.build();

	}

}
