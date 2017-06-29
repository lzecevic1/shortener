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
        return accountHelper.returnAccountResult(account);
    }
}