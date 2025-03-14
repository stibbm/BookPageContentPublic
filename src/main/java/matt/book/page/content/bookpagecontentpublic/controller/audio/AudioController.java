package matt.book.page.content.bookpagecontentpublic.controller.audio;

import matt.book.data.client.sqlmodel.audio.Audio;
import matt.book.page.content.bookpagecontentpublic.model.ApiMessage;
import matt.book.page.content.manager.AudioManager;
import matt.book.page.content.bookpagecontentpublic.clientwrapper.s3clientwrapper.S3ClientWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AudioController {
    private AudioManager audioManager;
    private S3ClientWrapper s3ClientWrapper;
        
    @Autowired
    public AudioController(
        AudioManager audioManager,
        S3ClientWrapper s3ClientWrapper
    ) {
        this.audioManager = audioManager;
        this.s3ClientWrapper = s3ClientWrapper;
    }
        
    @GetMapping("/getAudioByBookNameAndChapterNumber")
    @CrossOrigin("*")
    public @ResponseBody ResponseEntity<ApiMessage> getAudioByBookNameAndChapterNumber(
        @RequestParam String bookName,
        @RequestParam String chapterNumber,
        @RequestHeader("Authorization") String authToken
    ) {
        Audio audio = audioManager.getAudioByBookNameAndChapterNumber(
            bookName, chapterNumber, authToken
        );
        ApiMessage apiMessage = ApiMessage.builder()
            .body(audio)
            .error(false)
            .message("successfully retrieved the audio file")
            .build();
        return ResponseEntity.ok(apiMessage);
    }
  
}
