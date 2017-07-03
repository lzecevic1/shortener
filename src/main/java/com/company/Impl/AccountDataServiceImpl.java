package com.company.Impl;

import com.company.Interface.StatisticDataService;
import com.company.Model.AccountResult;
import com.company.Model.FullAccount;
import com.company.Util.RandomStringGenerator;
import com.company.Interface.AccountDataService;
import com.company.Model.Account;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class AccountDataServiceImpl implements AccountDataService {

    @Autowired
    private RandomStringGenerator randomStringGenerator;
    @Autowired
    private StatisticDataService statisticDataService;

    private Map<String, FullAccount> allAccounts;

    public AccountDataServiceImpl() {
        allAccounts = new HashMap<>();
    }

    public Boolean checkIfAccountExists(String accountID, String password){
        if(accountID == null || password == null) return false;
        FullAccount account = allAccounts.get(accountID);
        return account != null && password.equals(account.getPassword());
    }

    public String getPassword(String accountID) {
        return allAccounts.get(accountID).getPassword();
    }

    public void setAccountResult(AccountResult accountResult, Boolean success, String description, String password) {
        accountResult.setSuccess(success);
        accountResult.setDescription(description);
        accountResult.setPassword(password);
    }

    public Boolean isRegisteredAccountID(Account account) {
        return allAccounts.get(account.getAccountId()) != null;
    }

    public void registerAccount(Account account) {
        if(account != null) {
            String accountId = account.getAccountId();
            allAccounts.put(accountId, new FullAccount(accountId, randomStringGenerator.generateString()));
            statisticDataService.putNewAccount(accountId);
        }
    }
}

