package com.company.Controller;

import com.company.Interface.AccountDataService;
import com.company.Interface.StatisticDataService;
import com.company.Model.Account;
import com.company.Model.AccountResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/account")
public class AccountController {
    @Autowired
    private AccountDataService accountDataService;
    @Autowired
    private StatisticDataService statisticDataService;

    private AccountResult accountResult;
    public AccountController() {
        accountResult = new AccountResult();
    }

    @RequestMapping(method = RequestMethod.POST)
    public AccountResult registerAccount(@RequestBody Account account) {
        if (isAccountValid(account)) {
            if (!isRegistered(account)) {
                accountDataService.registerAccount(account);
                statisticDataService.putNewAccount(account.getAccountId());
                accountDataService.setAccountResult(accountResult, true, "Your account is opened", accountDataService.getPassword(account.getAccountId()));
            }
            else accountDataService.setAccountResult(accountResult, false, "Account with AccountID already exists.", null);
        }
        else accountDataService.setAccountResult(accountResult, false, "Account ID missing or incorrect!", null);

        return accountResult;
    }

    private boolean isRegistered(Account account) {
        return accountDataService.isRegisteredAccountID(account);
    }

    private boolean isAccountValid(Account account) {
        return account != null && account.getAccountId() != null;
    }
}