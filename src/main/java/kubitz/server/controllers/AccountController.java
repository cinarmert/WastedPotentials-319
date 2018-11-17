package kubitz.server.controllers;


import kubitz.server.database.accounts.model.Account;
import kubitz.server.database.accounts.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public Map<String, Object> getChallenge(@RequestBody Map<String, Object> body) {
        Account accountToAdd = new Account(Long.valueOf((String) body.get("id")), (String)body.get("name"));
        accountRepository.save(accountToAdd);
        return null;
    }

}
