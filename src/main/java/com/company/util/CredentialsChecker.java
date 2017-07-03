package com.company.util;

import com.company.service.AccountDataService;
import com.company.model.FullAccount;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Base64;

public class CredentialsChecker {
    @Autowired
    private AccountDataService accountDataService;

    public Boolean decodeAndCheckCredentials(String auth) {
        FullAccount accountToCheck = decode(auth.substring(6, auth.length()));
        if(accountToCheck == null) return false;
        return accountDataService.checkIfAccountExists(accountToCheck.getAccountId(), accountToCheck.getPassword());
    }

    public String getUsernameIfAccountExists(String auth){
        FullAccount account = decode(auth.substring(6, auth.length()));
        if(account == null || account.getAccountId() == null) return null;
        if(checkCredentials(account)) return account.getAccountId();
        return null;
    }

    private FullAccount decode(String auth){
        String decodedCredentials = new String(Base64.getDecoder().decode(auth));
        String[] credentials = parseString(decodedCredentials);
        if(credentials.length == 2) return new FullAccount (credentials[0], credentials[1]);
        return null;
    }

    private Boolean checkCredentials(FullAccount account){
        return accountDataService.checkIfAccountExists(account.getAccountId(), account.getPassword());
    }

    private String[] parseString(String auth) {
        return auth.split(":");
    }
}
