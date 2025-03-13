package matt.book.page.content.bookpagecontentpublic.service;

import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FileService {
    public void writeToFile(byte[] fileBytes, String fileName) throws IOException {
        File file = new File(fileName);
        FileUtils.writeByteArrayToFile(file, fileBytes);
    }

    public void deleteFile(String fileName) {
        File file = new File(fileName);
        file.delete();
    }

    public byte[] readFile(String fileName) throws IOException {
        File file = new File(fileName);
        byte[] fileBytes = FileUtils.readFileToByteArray(file);
        return fileBytes;
    }
}
