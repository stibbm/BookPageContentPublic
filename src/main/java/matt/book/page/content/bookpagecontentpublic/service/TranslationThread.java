package matt.book.page.content.bookpagecontentpublic.service;

import com.theokanning.openai.Usage;
import java.util.AbstractMap.SimpleEntry;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TranslationThread extends Thread {
    private final Integer NUMBER_OF_RETRIES = 5;
    private TranslationService translationService;
    public Boolean isCompleted = false;
    public String text;
    public String translatedText;
    public Usage usage;
    public String translateToLanguage;

    public TranslationThread() {
        this.translationService = new TranslationService();
    }

    @Override
    public void run() {
        for (int i=0;i<NUMBER_OF_RETRIES;i++) {
            try {
                SimpleEntry<String, Usage> translationResult = translationService.translateToLanguage(
                    text, translateToLanguage
                );
                String translatedText = translationResult.getKey();
                Usage usage = translationResult.getValue();
                log.info("text to translate: " + text);
                //this.translatedText = text;
                this.translatedText = translatedText;
                this.usage = usage;
                this.isCompleted = true;
                break;
            } catch (Exception e) {
                e.printStackTrace();
                if (i==NUMBER_OF_RETRIES-1) {
                    throw new RuntimeException("Failed to translate the text, ran out of retries");
                }
            }
        }
    }
}
