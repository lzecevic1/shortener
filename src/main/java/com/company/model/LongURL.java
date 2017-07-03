package com.company.model;

public class LongURL {
    private String url;
    private Integer redirectType;

    public LongURL() {
    }

    public LongURL(String url, Integer redirectType) {
        this.url = url;
        this.redirectType = redirectType;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LongURL longURL = (LongURL) o;

        if (url != null ? !url.equals(longURL.url) : longURL.url != null) return false;
        return redirectType != null ? redirectType.equals(longURL.redirectType) : longURL.redirectType == null;
    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (redirectType != null ? redirectType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LongURL{" +
                "url='" + url + '\'' +
                ", redirectType=" + redirectType +
                '}';
    }
}
