package matt.book.page.content.bookpagecontentpublic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenTransactionsData implements Serializable {
    private List<TokenTransaction> tokenTransactionList;
    private Long currentBalance;
}
