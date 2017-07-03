package com.company.model;

public class RegisteredURL extends LongURL{
    private String shortURL;

    public RegisteredURL(String url, Integer redirectType, String shortURL) {
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

        RegisteredURL that = (RegisteredURL) o;

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
        return "RegisteredURL{" +
                "longURL='" + getUrl() + '\'' +
                ", redirectType='" + getRedirectType() + '\'' +
                ", shortURL='" + shortURL + '\'' +
                '}';
    }
}
