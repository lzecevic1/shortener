package com.company.impl;

import com.company.service.StatisticDataService;
import com.company.model.FullAccount;
import com.company.util.RandomStringGenerator;
import com.company.service.AccountDataService;
import com.company.model.Account;
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

    public AccountDataServiceImpl(RandomStringGenerator randomStringGenerator, StatisticDataService statisticDataService) {
        this.randomStringGenerator = randomStringGenerator;
        this.statisticDataService = statisticDataService;
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

    public Map<String, FullAccount> getAllAccounts() {
        return allAccounts;
    }

    public Boolean isRegisteredAccountID(Account account) {
        return allAccounts.get(account.getAccountId()) != null;
    }

    public void registerAccount(Account account) {
        if(account != null) {
            String accountId = account.getAccountId();
            allAccounts.put(accountId, new FullAccount(accountId, randomStringGenerator.generateString()));
//            statisticDataService.putNewAccount(accountId);
        }
    }
}

