package matt.book.page.content.bookpagecontentpublic.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.slf4j.Logger;

public class ImageWriterService {
    public void writeImageFile(byte[] imageData, String fileType, String fileName) {
        File file = new File(fileName + "." + fileType);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(imageData);
            Logger log;
            log.info("File written successfully: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error writing the image file: " + e.getMessage());
        }
    }
}
