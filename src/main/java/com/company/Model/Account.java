package com.company.Model;

/**
 * Created by lzecevic on 6/21/17.
 */
public class Account {
    private String accountId;
    private String password;

    public Account(String accountId, String password) {
        this.accountId = accountId;
        this.password = password;
    }

    public Account() {

    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (accountId != null ? !accountId.equals(account.accountId) : account.accountId != null) return false;
        return password != null ? password.equals(account.password) : account.password == null;
    }

    @Override
    public int hashCode() {
        int result = accountId != null ? accountId.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
