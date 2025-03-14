package matt.book.page.content.bookpagecontentpublic.controller.book;

import java.awt.print.Book;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import matt.book.page.content.bookpagecontentpublic.model.ApiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BookController {
    private BookManager bookManager;
    
    @Autowired
    public BookController(
        BookManager bookManager
    ) {
        this.bookManager = bookManager;
    }
    
    @GetMapping("/getAllBooksPaged")
    @CrossOrigin("*")
    public @ResponseBody ResponseEntity<ApiMessage> getAllBooksPaged(
        @RequestParam String pageNumber,
        @RequestParam String pageSize,
        @RequestHeader("Authorization") String authToken
    ) throws IOException, InterruptedException {
        List<Book> bookList = bookManager.getAllBooksPaged(pageNumber, pageSize, authToken);
        ApiMessage apiMessage = ApiMessage.builder()
            .message("Successfully retrieved books list")
            .body(bookList)
            .error(false)
            .build();
        return ResponseEntity.ok(apiMessage);
    }
    
}
