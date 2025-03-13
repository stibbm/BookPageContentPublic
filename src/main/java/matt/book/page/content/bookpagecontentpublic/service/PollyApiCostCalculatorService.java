package matt.book.page.content.bookpagecontentpublic.service;

import static matt.book.page.content.bookpagecontentpublic.constants.Constants.ONE_MILLION;
import static matt.book.page.content.bookpagecontentpublic.constants.Constants.POLLY_API_COST_PER_MILLION_CHARS_IN_DOLLARS;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PollyApiCostCalculatorService {
    public Double calculateCostForCharCount(Long charCount) {
        Double cost = (charCount.doubleValue() * POLLY_API_COST_PER_MILLION_CHARS_IN_DOLLARS.doubleValue())
            / (ONE_MILLION.doubleValue());
        return cost;
    }
}
