package matt.book.page.content.bookpagecontentpublic.controller.account;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import matt.book.page.content.bookpagecontentpublic.manager.AccountManager;
import matt.book.page.content.bookpagecontentpublic.model.ApiMessage;
import matt.book.data.client.sqlmodel.account.Account;

@RestController
@Slf4j
public class AccountController {
    private AccountManager accountManager;
    
    @Autowired
    public AccountController(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    @GetMapping("/createAccount")
    @CrossOrigin("*")
    public @ResponseBody ResponseEntity<ApiMessage> createAccount(
        @RequestParam String fullName,
        @RequestHeader("Authorization") String authToken
    ) throws IOException, InterruptedException 
	{
        log.info("Create Account: ");
        log.info("fullName: " + fullName);
        Account account = accountManager.createAccount(
            fullName, authToken
        );
        ApiMessage apiMessage = ApiMessage.builder()
            .message("successfully created account")
            .error(false)
            .body(account)
            .build();
		return ResponseEntity.ok(apiMessage);
	}

	@GetMapping("/getAccount")
	@CrossOrigin("*")
	public @ResponseBody ResponseEntity<ApiMessage> getAccount(
		@RequestHeader("Authorization") String authToken
	) throws IOException, InterruptedException {
		log.info("getAccount: ");
		Account account = accountManager.getAccount(authToken);
		log.info("Account: " + account.toString());
		ApiMessage apiMessage = ApiMessage.builder()
			.message("successfully retrieved account")
			.error(false)
			.body(account)
			.build();
		return ResponseEntity.ok(apiMessage);
	}

}




