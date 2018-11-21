package kubitz.server.controllers;


import kubitz.server.database.accounts.model.Account;
import kubitz.server.database.accounts.repository.AccountRepository;
import kubitz.server.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    private AccountRepository accountRepository;
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public void login(@RequestBody String body) {
        Account accountToAdd = null;
        try {
            accountToAdd = JsonUtil.fromJson(body, Account.class);
        } catch (IOException e) {
            logger.error("could not parse the body to account, body: " + body);
            return;
        }

        logger.info("saving to accountdb: " + JsonUtil.toJson(accountToAdd));
        accountRepository.save(accountToAdd);
    }

}
