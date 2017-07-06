package com.company.impl;

import com.company.model.Account;
import com.company.model.FullAccount;
import com.company.repository.FullAccountRepository;
import com.company.service.AccountDataService;
import com.company.util.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class H2AccountDataServiceImpl implements AccountDataService {

    @Autowired
    private FullAccountRepository fullAccountRepository;
    @Autowired
    private RandomStringGenerator randomStringGenerator;

    public void registerAccount(Account account) {
        if(account != null){
            String accountId = account.getAccountId();
            fullAccountRepository.save(new FullAccount(accountId, randomStringGenerator.generateString()));
        }
    }

    public Boolean checkIfAccountExists(String accountID, String password) {
        return fullAccountRepository.findByAccountIdAndPassword(accountID, password) != null;
    }

    public Boolean isRegisteredAccountID(Account account) {
        return fullAccountRepository.findByAccountId(account.getAccountId()) != null;
    }

    public String getPassword(String accountID) {
        FullAccount account = fullAccountRepository.findByAccountId(accountID);
        return account.getPassword();
    }

    public Map<String, FullAccount> getAllAccounts() {
        return null;
    }
}
