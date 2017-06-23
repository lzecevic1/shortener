package com.company.Model;

/**
 * Created by lzecevic on 6/22/17.
 */

public class RegisteredURL {
    private String url;
    private Integer redirectType;
    private String shortURL;
    // private String message;

    public RegisteredURL() {
    }

    public RegisteredURL(String url, Integer redirectType, String shortURL) {
        this.url = url;
        this.redirectType = redirectType;
        this.shortURL = shortURL;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getRedirectType() {
        return redirectType;
    }

    public void setRedirectType(Integer redirectType) {
        this.redirectType = redirectType;
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

        RegisteredURL that = (RegisteredURL) o;

        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        return redirectType != null ? redirectType.equals(that.redirectType) : that.redirectType == null;
    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (redirectType != null ? redirectType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RegisteredURL{" +
                "url='" + url + '\'' +
                ", redirectType=" + redirectType +
                '}';
    }
}
