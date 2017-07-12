package com.company.util;

import com.company.service.AccountDataService;
import com.company.model.FullAccount;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Base64;
import java.util.Optional;

public class CredentialsChecker {
    @Autowired
    private AccountDataService accountDataService;

    public Boolean authenticate(String credentials) throws Exception {
        Optional<FullAccount> accountToCheck = decode(credentials.substring(6, credentials.length()));
        return accountToCheck.map(this::checkCredentials).orElse(false);
    }

    public Optional<String> getUsernameIfAccountExists(String credentials) throws Exception {
        Optional<FullAccount> accountToCheck = decode(credentials.substring(6, credentials.length()));
        return accountToCheck.flatMap(fullAccount -> Optional.ofNullable(fullAccount.getAccountId()));
    }

    private Optional<FullAccount> decode(String credentials) throws Exception {
        String decodedCredentials = new String(Base64.getDecoder().decode(credentials));
        String[] credentialArray = parseString(decodedCredentials);
        if (credentialArray.length == 2) {
            return Optional.of(new FullAccount(credentialArray[0], credentialArray[1]));
        }
        return Optional.empty();
    }

    private Boolean checkCredentials(FullAccount account) {
        return accountDataService.checkIfAccountExists(account.getAccountId(), account.getPassword());
    }

    private String[] parseString(String auth) {
        return auth.split(":");
    }
}
