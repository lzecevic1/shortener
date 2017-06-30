package com.company.Interface;

import com.company.Model.Account;
import com.company.Model.AccountResult;

public interface AccountDataService {
    void registerAccount(Account account);
    Boolean checkIfAccountExists(String accountID, String password);
    Boolean isRegisteredAccountID(Account account);
    String getPassword(String accountID);
    void setAccountResult(AccountResult accountResult, Boolean success, String description, String password);
}
