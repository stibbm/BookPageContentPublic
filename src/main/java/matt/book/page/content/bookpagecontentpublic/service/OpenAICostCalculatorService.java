package matt.book.page.content.bookpagecontentpublic.service;

import static matt.book.page.content.bookpagecontentpublic.constants.Constants.ONE_MILLION;

import lombok.extern.slf4j.Slf4j;
import matt.book.page.content.bookpagecontentpublic.constants.Constants;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OpenAIApiCostCalculatorService {

    public Double calculateCostForTokenCount(Long tokenCount) {
        Double cost = (tokenCount.doubleValue()
            * Constants.OPEN_API_COST_PER_MILLION_TOKENS_IN_DOLLARS.doubleValue())
            / (ONE_
        
        
        
        
        
        
        
        
        
        
        MILLION.doubleValue());
        return cost;
    }
}