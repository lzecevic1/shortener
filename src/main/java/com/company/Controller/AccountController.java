package com.company.Controller;

import com.company.Helper.AccountHelper;
import com.company.Model.Account;
import com.company.Model.AccountResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzecevic on 6/21/17.
 */

@RestController
@RequestMapping(value = "/account")
public class AccountController {
    @Autowired
    private AccountHelper accountHelper;

    @RequestMapping(method = RequestMethod.POST)
    public AccountResult registerAccount(@RequestBody Account account) {
        if (accountHelper.checkAccountID(account.getAccountId())) {
            accountHelper.registerAccount(account.getAccountId());
            return new AccountResult(true, "Your account is opened",
                                     accountHelper.getLastCreatedPassword(account.getAccountId()));
        }
        return new AccountResult(false, "Account with AccountID already exists.", null);
    }

    // Ovo je napravljeno za svrhe testiranja
    @RequestMapping(value = "/all")
    public List<Account> getAll(){
        return new ArrayList<>(accountHelper.getAllAccounts().values());
    }
}