package com.company.impl;

import com.company.model.Account;
import com.company.model.FullAccount;
import com.company.repository.FullAccountRepository;
import com.company.service.AccountDataService;
import com.company.util.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class H2AccountDataServiceImpl implements AccountDataService {

    @Autowired
    private FullAccountRepository fullAccountRepository;
    @Autowired
    private RandomStringGenerator randomStringGenerator;

    public void registerAccount(Account account) throws Exception {
        String accountId = account.getAccountId();
        if (accountId == null) throw new Exception("Account ID missing!");
        if (isRegisteredAccountID(account)) {
            throw new Exception("Account with AccountID already exists.");
        }
        fullAccountRepository.save(new FullAccount(accountId, randomStringGenerator.generateString()));
    }

    public Boolean checkIfAccountExists(String accountID, String password) {
        Optional<FullAccount> accountFromDB = fullAccountRepository.findByAccountIdAndPassword(accountID, password);
        return accountFromDB.isPresent();
    }

    public Boolean isRegisteredAccountID(Account account) {
        Optional<FullAccount> accountFromDB = getAccountFromDB(account.getAccountId());
        return accountFromDB.isPresent();
    }

    public Optional<String> getPassword(String accountID) {
        Optional<FullAccount> accountFromDB = getAccountFromDB(accountID);
        return accountFromDB.flatMap(fullAccount -> Optional.ofNullable(fullAccount.getPassword()));
    }

    public Map<String, FullAccount> getAllAccounts() {
        List<FullAccount> allAccounts = (ArrayList<FullAccount>) fullAccountRepository.findAll();
        Map<String, FullAccount> mapOfAllAccounts = new HashMap<>();
        allAccounts.forEach(account -> mapOfAllAccounts.put(account.getAccountId(), account));
        return mapOfAllAccounts;
    }

    private Optional<FullAccount> getAccountFromDB(String accountID) {
        return fullAccountRepository.findByAccountId(accountID);
    }
}
