package com.company.Helper;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Base64;

/**
 * Created by lzecevic on 6/23/17.
 */
public class CredentialsChecker {
    @Autowired
    private AccountHelper accountHelper;

    public Boolean checkCredentials(String auth) {
        String decodedCredentials = new String(Base64.getDecoder().decode(auth));
        String[] credentials = parseString(decodedCredentials);
        String accountID = credentials[0];
        String password = credentials[1];
        return accountHelper.checkIfAccountExists(accountID, password);
    }

    private String[] parseString(String auth) {
        return auth.split(":");
    }

}
