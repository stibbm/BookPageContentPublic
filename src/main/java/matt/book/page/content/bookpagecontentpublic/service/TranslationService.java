package matt.book.page.content.bookpagecontentpublic.service;

import com.google.gson.Gson;
import com.theokanning.openai.Usage;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import matt.book.page.content.bookpagecontentpublic.bean.OpenAiServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TranslationService {

    private final Gson gson = new Gson();

    private OpenAiService openAiService;
    private StringUtilService stringUtilService;

    @Autowired
    public TranslationService(OpenAiService openAiService, StringUtilService stringUtilService) {
        this.openAiService = openAiService;
        this.stringUtilService = stringUtilService;
    }

    public TranslationService() {
        OpenAiServiceBean openAiServiceBean = new OpenAiServiceBean();
        this.openAiService = openAiServiceBean.provideOpenAiServiceBean();
        this.stringUtilService = new StringUtilService();
    }

    public AbstractMap.SimpleEntry<String, List<Usage>> translateToLanguageLargeText(String text,
        String translateToLanguage) {
        List<String> textChunkList = stringUtilService.splitTextIntoChunks(text, TRANSLATION_CHUNK_SIZE);
        List<String> translatedTextChunkList = new ArrayList<>();
        List<Usage> usageList = new ArrayList<>();
        StringBuilder translatedTextBuilder = new StringBuilder();
        for (String textChunk : textChunkList) {
            SimpleEntry<String, Usage> translationResult = translateToLanguage(textChunk, translateToLanguage);
            String translatedTextChunk = translationResult.getKey();
            Usage usage = translationResult.getValue();
            usageList.add(usage);
            translatedTextBuilder.append(translatedTextChunk);
        }
        return new SimpleEntry<>(translatedTextBuilder.toString(), usageList);
    }

    public AbstractMap.SimpleEntry<String, Usage> translateToLanguage(String text, String translateToLanguage) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setRole("user");
        chatMessage.setContent("Translate this to " + translateToLanguage + "\"" + text + "\"");
        List<ChatMessage> chatMessageList = new ArrayList<>();
        chatMessageList.add(chatMessage);
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
            .model("gpt-4")
            // .model("gpt-3.5-turbo")
            .temperature(0.2)
            .topP(0.1)
            .messages(chatMessageList)
            .build();
        ChatCompletionResult chatCompletionResult = this.openAiService.createChatCompletion(
            chatCompletionRequest);
        String translatedText = chatCompletionResult.getChoices().get(0).getMessage().getContent();
        return new SimpleEntry<>(translatedText, chatCompletionResult.getUsage());
    }

}
