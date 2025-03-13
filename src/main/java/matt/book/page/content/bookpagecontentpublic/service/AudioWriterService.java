package matt.book.page.content.bookpagecontentpublic.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
@Service
public class AudioWriterService {
    public void writeAudioFile(byte[] audioBytes, String fileType, String fileName) {
        File file = new File(fileName + "." + fileType);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(audioBytes);
            log.info("File written successfully: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error writing the audio file: " + e.getMessage());
        }
    }
}
