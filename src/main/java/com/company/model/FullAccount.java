package com.company.model;

import javax.persistence.Entity;

@Entity
public class FullAccount extends Account {

    private String password;

    public FullAccount() {
        super();
    }

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
                "accountId='" + accountId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
