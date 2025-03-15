package matt.book.page.content.bookpagecontentpublic.controller.textblock;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TextBlockController {

    private TextBlockManager textBlockManager;

    @Autowired
    public TextBlockController(TextBlockManager textBlockManager) {
        this.textBlockManager = textBlockManager;
    }

    @GetMapping("/getTextBlockByBookNameAndChapterNumber")
    @CrossOrigin("*")
    public @ResponseBody ResponseEntity<ApiMessage> getTextBlockByBookNameAndChapterNumber(
        @RequestParam String bookName,
        @RequestParam String chapterNumber,
        @RequestHeader("Authorization") String authToken
    ) throws IOException, InterruptedException {
        log.info("/getTextBlockByBookNameChangeChapterNumber: " + "bookName: " + bookName + "chapterNumber"+ chapterNumber);

        TextBlock textBlock = textBlockManager.getTextBlockByBookNameAndChapterNumber(
            bookName,
            chapterNumber,
            authToken
        );
        ApiMessage apiMessage = ApiMessage.builder()
            .body(textBlock)
            .message("successfully retrieved text block")
            .error(false)
            .build();
        return ResponseEntity.ok(apiMessage);
    }

    @PostMapping("/createTextBlock")
    @CrossOrigin("*")
    public @ResponseBody ResponseEntity<ApiMessage> createTextBlock(
        @ModelAttribute CreateTextBlockFormData createTextBlockFormData,
        @RequestHeader("Authorization") String authToken
    ) throws IOException, InterruptedException {
        TextBlock textBlock = textBlockManager.createTextBlock(
            createTextBlockFormData.getBookName(),
            createTextBlockFormData.getChapterNumber(),
            createTextBlockFormData.getTextBlockText(),
            authToken
        );
        ApiMessage apiMessage = new ApiMessage(
            textBlock,
            "successfully created text block",
            false
        );
        return ResponseEntity.ok(apiMessage);
    }
}
