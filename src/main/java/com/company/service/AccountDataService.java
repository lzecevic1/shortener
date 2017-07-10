package com.company.service;

import com.company.model.Account;
import com.company.model.FullAccount;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public interface AccountDataService {
    void registerAccount(Account account) throws Exception;
    Boolean checkIfAccountExists(String accountID, String password);
    Boolean isRegisteredAccountID(Account account);
    Optional<String> getPassword(String accountID);
    Map<String, FullAccount> getAllAccounts();
}
