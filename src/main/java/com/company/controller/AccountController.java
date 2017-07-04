package com.company.controller;

import com.company.service.AccountDataService;
import com.company.service.StatisticDataService;
import com.company.model.Account;
import com.company.model.AccountResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/account")
public class AccountController {
    @Autowired
    private AccountDataService accountDataService;
    @Autowired
    private StatisticDataService statisticDataService;

    @RequestMapping(method = RequestMethod.POST)
    public AccountResult registerAccount(@RequestBody Account account) {
        AccountResult accountResult = new AccountResult();
        if (isAccountValid(account)) {
            if (!isRegistered(account)) {
                accountDataService.registerAccount(account);
                statisticDataService.putNewAccount(account.getAccountId());
                setAccountResult(accountResult, true, "Your account is opened", accountDataService.getPassword(account.getAccountId()));
            } else setAccountResult(accountResult, false, "Account with AccountID already exists.", null);
        }
        else setAccountResult(accountResult, false, "Account ID missing or incorrect!", null);

        return accountResult;
    }

    private boolean isRegistered(Account account) {
        return accountDataService.isRegisteredAccountID(account);
    }

    private boolean isAccountValid(Account account) {
        return account != null && account.getAccountId() != null;
    }

    private void setAccountResult(AccountResult accountResult, Boolean success, String description, String password) {
        accountResult.setSuccess(success);
        accountResult.setDescription(description);
        accountResult.setPassword(password);
    }
}