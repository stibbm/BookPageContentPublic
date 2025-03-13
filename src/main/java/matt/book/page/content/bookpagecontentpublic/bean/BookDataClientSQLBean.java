package matt.book.page.content.bookpagecontentpublic.bean;

import org.springframework.context.annotation.Bean;
import matt.book.data.client.BookDataClientSQL;

public class BookDataClientSQLBean {
	private String endpointUrl = "<bookdataservicendpointurl>";

	@Bean
	public BookDataClientSQL provideBookDataClientSQL() {
		BookDataClientSQL bookDataClientSQL = new BookDataClientSQL(
			endpointUrl
		);
		return bookDataClientSQL;
	}
}

