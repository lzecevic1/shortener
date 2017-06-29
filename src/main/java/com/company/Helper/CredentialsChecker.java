package com.company.Helper;

import com.company.Model.Account;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Base64;

/**
 * Created by lzecevic on 6/23/17.
 */
public class CredentialsChecker {
    @Autowired
    private AccountHelper accountHelper;

    public Boolean decodeAndCheckCredentials(String auth) {
        Account accountToCheck = decode(auth);
        return accountHelper.checkIfAccountExists(accountToCheck.getAccountId(), accountToCheck.getPassword());
    }

    public String getUsernameIfAccountExists(String auth){
        Account account = decode(auth);
        if(checkCredentials(account)) return account.getAccountId();
        return null;
    }

    private Account decode(String auth){
        String decodedCredentials = new String(Base64.getDecoder().decode(auth));
        String[] credentials = parseString(decodedCredentials);
        return new Account (credentials[0], credentials[1]);
    }

    private Boolean checkCredentials(Account account){
        return accountHelper.checkIfAccountExists(account.getAccountId(), account.getPassword());
    }

    private String[] parseString(String auth) {
        return auth.split(":");
    }

}
