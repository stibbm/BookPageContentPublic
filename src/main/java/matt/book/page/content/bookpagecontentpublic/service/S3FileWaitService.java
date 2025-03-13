package matt.book.page.content.bookpagecontentpublic.service;

import java.io.IOException;
import java.time.Instant;
import java.util.Vector;
import lombok.extern.slf4j.Slf4j;
import matt.book.page.content.bookpagecontentpublic.clientwrapper.s3clientwrapper.S3ClientWrapper;
import matt.book.page.content.bookpagecontentpublic.model.AudioData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Waits for file to be created and then returns gets and returns the file once it has been created
 */

@Slf4j
@Service
public class S3FileWaitService {

    private S3ClientWrapper s3ClientWrapper;

    @Autowired
    public S3FileWaitService(S3ClientWrapper s3ClientWrapper) {
        this.s3ClientWrapper = s3ClientWrapper;
    }

    public AudioData waitForFile(String s3Bucket, String s3KeyPrefix, Long waitInterval,
        Integer numberOfChecks) throws InterruptedException, IOException {
        for (int i = 0; i < numberOfChecks; i++) {
            Thread.sleep(waitInterval);
            if (s3ClientWrapper.doesS3FileWithPrefixExist(s3Bucket, s3KeyPrefix)) {
                Vector<String> fileList = s3ClientWrapper.listS3Files(s3Bucket, s3KeyPrefix);
                if (fileList.size() > 1) {
                    log.warn("there are two files with the same prefix, which there shouldn't be, "
                        + "I am just going to arbitrarily pick one");
                }
                String fileName = fileList.get(0);
                byte[] audioBytes = s3ClientWrapper.getS3FileBytes(s3Bucket, fileName);
                String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
                AudioData audioData = AudioData.builder().audioBytes(audioBytes).fileType(fileType)
                    .build();
                return audioData;
            }
        }
        throw new RuntimeException("failed to retrieve the file within the specified time limit");
    }
}
