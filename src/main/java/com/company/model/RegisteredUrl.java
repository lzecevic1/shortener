package com.company.model;

import javax.persistence.Entity;

@Entity
public class RegisteredUrl extends LongUrl {

    private String shortURL;

    public RegisteredUrl() {
        super();
    }

    public RegisteredUrl(String url, Integer redirectType, String shortURL) {
        super(url, redirectType);
        this.shortURL = shortURL;
    }

    public String getShortURL() {
        return shortURL;
    }

    public void setShortURL(String shortURL) {
        this.shortURL = shortURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RegisteredUrl that = (RegisteredUrl) o;

        return shortURL != null ? shortURL.equals(that.shortURL) : that.shortURL == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (shortURL != null ? shortURL.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RegisteredUrl{" +
                "longURL='" + getUrl() + '\'' +
                ", redirectType='" + getRedirectType() + '\'' +
                ", shortURL='" + shortURL + '\'' +
                '}';
    }
}
