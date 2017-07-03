package com.company.service;

import com.company.model.Account;
import com.company.model.FullAccount;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface AccountDataService {
    void registerAccount(Account account);
    Boolean checkIfAccountExists(String accountID, String password);
    Boolean isRegisteredAccountID(Account account);
    String getPassword(String accountID);
    Map<String, FullAccount> getAllAccounts();
}
