package com.company.Helper;

import com.company.Model.Account;
import com.company.Model.AccountResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by lzecevic on 6/21/17.
 */

@Component
@Scope("singleton")
public class AccountHelper {

    @Autowired
    private RandomStringGenerator randomStringGenerator;
    @Autowired
    private StatisticHelper statisticHelper;

    private Map<String, Account> allAccounts;

    public AccountHelper() {
        this.allAccounts = new HashMap<>();
    }

    public AccountResult returnAccountResult(Account account) {
        if(account.getAccountId() == null) return new AccountResult(false, "Account ID missing or incorrect", null);

        if (checkAccountID(account.getAccountId())) {
            registerAccount(account.getAccountId());
            return new AccountResult(true, "Your account is opened",
                    getLastCreatedPassword(account.getAccountId()));
        }

        return new AccountResult(false, "Account with AccountID already exists.", null);
    }

    // Vraca true ako ne postoji nijedan account sa datim ID-em
    private Boolean checkAccountID(String accountId){
        return allAccounts.get(accountId) == null;
    }

    // Metoda se koristi za provjeru postojanja accounta prilikom registracije URL-a (iz RegisterHelpera)
    Boolean checkIfAccountExists(String accountID, String password){
        return isExistingAccount(accountID, password);
    }

    private boolean isExistingAccount(String accountID, String password) {
        return allAccounts.get(accountID) != null && password.equals(allAccounts.get(accountID).getPassword());
    }

    private void registerAccount(String accountId) {
        allAccounts.put(accountId, new Account(accountId, randomStringGenerator.generateString()));
        statisticHelper.putNewAccount(accountId);
    }

    private String getLastCreatedPassword(String accountID) {
        return allAccounts.get(accountID).getPassword();
    }
}
