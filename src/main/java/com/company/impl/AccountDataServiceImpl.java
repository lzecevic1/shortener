package com.company.impl;

import com.company.service.StatisticDataService;
import com.company.model.FullAccount;
import com.company.util.RandomStringGenerator;
import com.company.service.AccountDataService;
import com.company.model.Account;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    public Boolean checkIfAccountExists(String accountID, String password) {
        Optional<FullAccount> accountFromMap = Optional.ofNullable(allAccounts.get(accountID));
        return accountFromMap.filter(fullAccount -> password.equals(fullAccount.getPassword())).isPresent();
    }

    public Optional<String> getPassword(String accountID) {
        Optional<FullAccount> accountFromMap = Optional.ofNullable(allAccounts.get(accountID));
        return accountFromMap.map(FullAccount::getPassword);
    }

    public Map<String, FullAccount> getAllAccounts() {
        return allAccounts;
    }

    public Boolean isRegisteredAccountID(Account account) {
        Optional<FullAccount> accountFromMap = Optional.ofNullable(allAccounts.get(account.getAccountId()));
        return accountFromMap.isPresent();
    }

    public void registerAccount(Account account) {
        String accountId = account.getAccountId();
        allAccounts.put(accountId, new FullAccount(accountId, randomStringGenerator.generateString()));
    }
}

