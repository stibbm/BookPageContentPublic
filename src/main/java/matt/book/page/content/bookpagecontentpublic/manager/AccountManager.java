package matt.book.page.content.bookpagecontentpublic.manager;


import com.stripe.model.Account;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountManager {
    private BookDataClientSQL bookDataClientSQL;

    @Autowired
    public AccountManager(BookDataClientSQL bookDataClientSQL) {
        this.bookDataClientSQL = bookDataClientSQL;
    }

    public Account createAccount(String fullName, String authToken)
        throws IOException, InterruptedException {
        Account account = bookDataClientSQL.createAccount(fullName, authToken);
        return account;
    }

    public Account getAccount(String authToken) throws IOException, InterruptedException {
        Account account = bookDataClientSQL.getAccount(authToken);
        return account;
    }
}
