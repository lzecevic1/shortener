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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        FullAccount account = (FullAccount) o;

        return password != null ? password.equals(account.password) : account.password == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}