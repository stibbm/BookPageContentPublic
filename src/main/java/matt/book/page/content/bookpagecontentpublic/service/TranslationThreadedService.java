package matt.book.page.content.bookpagecontentpublic.service;

import com.theokanning.openai.Usage;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TranslationThreadedService {

    private StringUtilService stringUtilService;

    @Autowired
    public TranslationThreadedService(StringUtilService stringUtilService) {
        this.stringUtilService = stringUtilService;
    }

    public SimpleEntry<String, List<Usage>> translateText(String text, String translateToLanguage) {
        List<String> textChunkList = stringUtilService.splitTextIntoChunks(text,
            TRANSLATION_CHUNK_SIZE);
        TranslationThread[] translationThreadArray = new TranslationThread[textChunkList.size()];
        for (int i=0;i<translationThreadArray.length;i++) {
            translationThreadArray[i] = new TranslationThread();
            translationThreadArray[i].text = textChunkList.get(i);
            translationThreadArray[i].translateToLanguage = translateToLanguage;
            translationThreadArray[i].start();
        }
        // sleep 10 seconds and check again if done. 300 seconds = timeout so 30 * 10 second wait
        for (int i=0;i<30;i++) {
            if (isDone(translationThreadArray)) {
                return collectTranslatedText(translationThreadArray);
            }
            else {
                try {
                    Thread.sleep(10000);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        throw new RuntimeException("failed to translate text");
    }

    public SimpleEntry<String, List<Usage>> collectTranslatedText(TranslationThread[] translationThreadArray) {
        StringBuilder translatedTextBuilder = new StringBuilder();
        List<Usage> usageList = new ArrayList<>();
        for (int i=0;i<translationThreadArray.length;i++) {
            translatedTextBuilder.append(translationThreadArray[i].translatedText);
            usageList.add(translationThreadArray[i].usage);
        }
        return new SimpleEntry<>(translatedTextBuilder.toString(), usageList);
    }

    public Boolean isDone(TranslationThread[] translationThreadArray) {
        for (TranslationThread translationThread : translationThreadArray) {
            if (!translationThread.isCompleted) {
                return false;
            }
        }
        return true;
    }
}
