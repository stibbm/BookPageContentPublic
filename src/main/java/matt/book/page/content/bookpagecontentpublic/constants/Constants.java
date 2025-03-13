package matt.book.page.content.bookpagecontentpublic.constants;

import com.google.gson.Gson;

public class Constants {
	public static final String S3_BUCKET = "<s3 bucket>";
	public static final String AUDIO_S3_PREFIX = "<audio s3 prefix>";
	public static final String TEXT_S3_PREFIX = "<text s3 prefix>";
	public static final Long DEFAULT_AUDIO_NUMBER = 1L;
	public static final int MAX_PAGED_REQUEST_SIZE = 2000000;
	public static final int START_PAGE_INDEX = 0;
	public static final Long NO_VIEWS = 0L;
	public static final Integer TRANSLATION_CHUNK_SIZE = 500;
	public static final Gson GSON = new Gson();
	public static final Long TEN_SECONDS_IN_MILLI = 10 * 1000L;
	public static final Long OPEN_API_COST_PER_MILLION_TOKENS_IN_DOLLARS = 30L;
	public static final Long POLLY_API_COST_PER_MILLION_CHARS_IN_DOLLARS = 16L;
	public static final Long OPEN_API_COST_PER_MILLION_TOKENS_IN_CENTS = OPEN_API_COST_PER_MILLION_TOKENS_IN_DOLLARS * 100L;
	public static final Long POLLY_API_COST_PER_MILLION_CHARS_IN_CENTS = POLLY_API_COST_PER_MILLION_CHARS_IN_DOLLARS * 100L;

	public static final Long ONE_MILLION = 1000000L;
	public static final Long TOKEN_COST_PER_THOUSAND_IN_DOLLARS = 1L;
	public static final Long ONE_THOUSAND = 1000L;
	public static final Long YOUTUBE_VIDEO_CHAPTER_COUNT = 5L;
	public static final Double PER_MILLION_CHARS_TRUE_COST_ESTIMATE_DOLLARS = 76.22;
	public static final Double PER_MILLION_CHARS_TRUE_COST_ESTIMATE_CENTS = PER_MILLION_CHARS_TRUE_COST_ESTIMATE_DOLLARS
			* 100;
	public static final Double PRICE_MULTIPLIER = 1.3;
	public static final Double PER_MILLION_CHARS_PRICE_DOLLARS = PER_MILLION_CHARS_TRUE_COST_ESTIMATE_DOLLARS
			* PRICE_MULTIPLIER;
	public static final String YOUTUBE_VIDEO_URL_TEMPLATE = "https://www.youtube.com/watch?v={videoId}";

	public static final Long MAX_PRE_TRANSLATION_TEXT_SIZE = 40000L;
	public static final Long PRICE_CENTS_TO_TOKENS_MULTIPLIER = 10L;
	public static final Long PRICE_DOLLARS_TO_TOKENS_MULTIPLIER = PRICE_CENTS_TO_TOKENS_MULTIPLIER * 100L;
	public static final String MY_API_KEY = "<my api key>";
	public static final Long SECONDS_IN_7_DAYS = 24L * 60L * 60L * 7L;
}
