package matt.book.page.content.bookpagecontentpublic.controller.book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import matt.book.page.content.bookpagecontentpublic.model.ApiMessage;
import matt.book.page.content.model.BookDetailsCard;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BookDetailsCardController {
	    private BookDetailsCardManager bookDetailsCardManager;

    @Autowired
    public BookDetailsCardController(
        BookDetailsCardManager bookDetailsCardManager
    ) {
        this.bookDetailsCardManager = bookDetailsCardManager;
    }

    @GetMapping("/getBookDetailsCardByBookName")
    @CrossOrigin("*")
    public @ResponseBody ResponseEntity<ApiMessage> getBookDetailsCardByBookName(
        @RequestParam String bookName,
        @RequestHeader("Authorization") String authToken
    ) throws IOException, InterruptedException {
        log.info("getBookDetailsCardByBookName");
        log.info(bookName);
        BookDetailsCard bookDetailsCard =
            bookDetailsCardManager.getBookDetailsCardByBookName(bookName, authToken);
        ApiMessage apiMessage = ApiMessage.builder()
            .body(bookDetailsCard)
            .message("successfully retrieved bookDetailsCard")
            .error(false)
            .build();
        //log.info(apiMessage);
        return ResponseEntity.ok(apiMessage);
    }

    @GetMapping("/getBookDetailsCardByBookNameBatched")
    @CrossOrigin("*")
    public @ResponseBody ResponseEntity<ApiMessage> getBookDetailsCardByBookNameBatched(
        @RequestParam List<String> bookNameList,
        @RequestHeader("Authorization") String authToken
    ) throws IOException, InterruptedException {
        List<BookDetailsCard> bookDetailsCardList = new ArrayList<>();
        for (String bookName : bookNameList) {
            BookDetailsCard bookDetailsCard =
                bookDetailsCardManager.getBookDetailsCardByBookName(bookName, authToken);
            bookDetailsCardList.add(bookDetailsCard);
        }
        ApiMessage apiMessage = ApiMessage.builder()
            .body(bookDetailsCardList)
            .message("successfully retrieved bokDetailsCardList")
            .error(false)
            .build();
        return ResponseEntity.ok(apiMessage);
    }
}
