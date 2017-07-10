package com.company.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String accountId;
    private String longUrl;
    private Integer numberOfVisits;

    public Statistic() {
    }

    public Statistic(String accountId, String longUrl, Integer numberOfVisits) {
        this.accountId = accountId;
        this.longUrl = longUrl;
        this.numberOfVisits = numberOfVisits;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public Integer getNumberOfVisits() {
        return numberOfVisits;
    }

    public void setNumberOfVisits(Integer numberOfVisits) {
        this.numberOfVisits = numberOfVisits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Statistic statistic = (Statistic) o;

        if (id != null ? !id.equals(statistic.id) : statistic.id != null) return false;
        if (accountId != null ? !accountId.equals(statistic.accountId) : statistic.accountId != null) return false;
        if (longUrl != null ? !longUrl.equals(statistic.longUrl) : statistic.longUrl != null) return false;
        return numberOfVisits != null ? numberOfVisits.equals(statistic.numberOfVisits) : statistic.numberOfVisits == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (accountId != null ? accountId.hashCode() : 0);
        result = 31 * result + (longUrl != null ? longUrl.hashCode() : 0);
        result = 31 * result + (numberOfVisits != null ? numberOfVisits.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "id=" + id +
                ", accountId='" + accountId + '\'' +
                ", longUrl='" + longUrl + '\'' +
                ", numberOfVisits=" + numberOfVisits +
                '}';
    }
}
