package com.company.util;

import com.company.service.AccountDataService;
import com.company.model.FullAccount;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Base64;

public class CredentialsChecker {
    @Autowired
    private AccountDataService accountDataService;

    public Boolean authenticate(String credentials) {
        FullAccount accountToCheck = decode(credentials.substring(6, credentials.length()));
        if(accountToCheck == null) return false;
        return checkCredentials(accountToCheck);
    }

    public String getUsernameIfAccountExists(String credentials){
        FullAccount account = decode(credentials.substring(6, credentials.length()));
        if(account == null || account.getAccountId() == null) return null;
        if(checkCredentials(account)) return account.getAccountId();
        return null;
    }

    private FullAccount decode(String credentials){
        String decodedCredentials = new String(Base64.getDecoder().decode(credentials));
        String[] credentialArray = parseString(decodedCredentials);
        if(credentialArray.length == 2) return new FullAccount (credentialArray[0], credentialArray[1]);
        return null;
    }

    private Boolean checkCredentials(FullAccount account){
        return accountDataService.checkIfAccountExists(account.getAccountId(), account.getPassword());
    }

    private String[] parseString(String auth) {
        return auth.split(":");
    }
}
