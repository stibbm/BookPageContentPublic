package matt.book.page.content.bookpagecontentpublic.service;


import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import matt.book.page.content.bookpagecontentpublic.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IdService {

    private BookDataClientSQL bookDataClientSQL;

    @Autowired
    public IdService(BookDataClientSQL bookDataClientSQL) {
        this.bookDataClientSQL = bookDataClientSQL;
    }

    public boolean doesAudioExist(String bookName, String chapterNumber, String authToken)
        throws IOException, InterruptedException {
        List<Audio> audioList = bookDataClientSQL.getAudiosByBookNameAndChapterNumber(bookName,
            chapterNumber, authToken);
        if (audioList == null || audioList.size() == 0) {
            return false;
        }
        if (audioList.size() > 0) {
            return true;
        }
        throw new RuntimeException("Failed to check whether audio already exists");
    }

    public Long getNextAudioNumber(String bookName, String chapterNumber, String authToken)
        throws IOException, InterruptedException {
        Long audioNumber = 1L;
        List<Audio> audioList = bookDataClientSQL.getAudiosByBookNameAndChapterNumber(bookName,
            chapterNumber, authToken);
        if (audioList.size() > 0) {
            Long largestAudioNumber = audioList.get(audioList.size() - 1).getAudioId()
                .getAudioNumber();
            audioNumber = largestAudioNumber + 1L;
        }
        return audioNumber;
    }

    public String getAudioS3KeyPrefix(String bookName, Long chapterNumber, Long audioNumber) {
        return removeInvalidS3Characters(
            Constants.AUDIO_S3_PREFIX + "/" + bookName + "/" + chapterNumber + "/" + audioNumber);
    }

    public String getTextS3KeyPrefix(Long bookNumber, Long chapterNumber) {
        String s3KeyPrefix = Constants.TEXT_S3_PREFIX + "/" + bookNumber.toString() + "/" + chapterNumber.toString();
        return removeInvalidS3Characters(s3KeyPrefix);
    }

    public String getTextS3PrefixForChapterRange(Long bookNumber, Long startChapterNumber, Long endChapterNumber) {
        String s3KeyPrefix = Constants.TEXT_S3_PREFIX + "/" + bookNumber.toString() + "/" + startChapterNumber.toString() + "/" + endChapterNumber.toString();
        return removeInvalidS3Characters(s3KeyPrefix);
    }

    public String getAudioS3KeyPrefixForChapterRange(String bookName, Long startChapterNumber, Long endChapterNumber, Long audioNumber) {
        return removeInvalidS3Characters(
            Constants.AUDIO_S3_PREFIX + "/" + bookName + "/" + startChapterNumber + "/" + endChapterNumber + "/" + audioNumber);
    }

    public String removeInvalidS3Characters(String input) {
        // Regex that matches characters not listed in the original regex
        String regex = "[^0-9a-zA-Z/!\\-_.*'():;$@=+,?&]";

        // Replace characters not matching the regex with empty string
        return input.replaceAll(regex, "");
    }

    public String generateRandomFileName(String fileType) {
        return removeInvalidS3Characters(UUID.randomUUID().toString() + "." + fileType);
    }

    public Long getNextChapterNumber(
        String bookName,
        String authToken
    ) throws IOException, InterruptedException {
        List<Chapter> existingChapterList = bookDataClientSQL.getChaptersByBookNamePaged(
            bookName, 0, 2000000, authToken
        );
        Collections.sort(existingChapterList,
            (o1, o2) -> Integer.parseInt(o1.getChapterId().getChapterNumber().toString()) -
                Integer.parseInt(o2.getChapterId().getChapterNumber().toString()));
        Long nextChapterNumber = 1L;
        if (existingChapterList.size() > 0) {
            nextChapterNumber =
                existingChapterList.get(existingChapterList.size() - 1).getChapterId()
                    .getChapterNumber() + 1L;
        }
        return nextChapterNumber;
    }

    public String generateRandomString() {
        return UUID.randomUUID().toString();
    }
}
