package matt.book.page.content.bookpagecontentpublic.constants;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

public class Environment {
	public static final String STRIPE_SECRET_API_KEY = "<stripe secret api key>";
	public static final String CLIENT_SECRETS = "<path to client secrest file>.json";
	  public static final Collection<String> SCOPES =
	        Arrays.asList("https://www.googleapis.com/auth/youtube.upload");
	public static final String APPLICATION_NAME = "<application name>";
	public static final File DATA_STORE_DIR = new File(System.getProperty("user.home"), "<path to youtube credentials stored file>");
	public static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

}
