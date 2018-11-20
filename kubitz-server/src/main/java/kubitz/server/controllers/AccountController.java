package kubitz.server.controllers;


import kubitz.server.database.accounts.model.Account;
import kubitz.server.database.accounts.repository.AccountRepository;
import kubitz.server.util.JsonUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.logging.LogManager;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    private AccountRepository accountRepository;

    @Autowired
    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody String body) {
        Account accountToAdd = null;
        try {
            accountToAdd = JsonUtil.fromJson(body, Account.class);
        } catch (IOException e) {
            //Todo add logger - projectwise
            e.printStackTrace();
            return JsonUtil.toJson(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));

        }
        accountRepository.save(accountToAdd);
        return JsonUtil.toJson(accountToAdd);
    }

}
