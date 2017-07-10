package com.company.controller;

import com.company.service.AccountDataService;
import com.company.service.StatisticDataService;
import com.company.model.Account;
import com.company.model.AccountResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(value = "/account")
public class AccountController {
    @Autowired
    private AccountDataService accountDataService;

    @RequestMapping(method = RequestMethod.POST)
    public AccountResult registerAccount(@RequestBody Account account) {
        AccountResult accountResult = new AccountResult();
        try {
            accountDataService.registerAccount(account);
            setAccountResult(accountResult, true, "Your account is opened!", getPassword(account));
        } catch (IllegalArgumentException exception) {
            System.out.println("Saving to database failed. " + exception.getMessage());
        } catch (Exception exception) {
            setAccountResult(accountResult, false, exception.getMessage(), null);
        }
        return accountResult;
    }

    private String getPassword(Account account) {
        return accountDataService.getPassword(account.getAccountId()).orElse(null);
    }

    private void setAccountResult(AccountResult accountResult, Boolean success, String description, String password) {
        accountResult.setSuccess(success);
        accountResult.setDescription(description);
        accountResult.setPassword(password);
    }
}