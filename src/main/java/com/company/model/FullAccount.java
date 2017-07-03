package com.company.model;

public class FullAccount extends Account {
    private String password;

    public FullAccount(String accountId, String password) {
        super(accountId);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "FullAccount{" +
                "accountId='" + getAccountId() + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
