package com.company.model;

public class VisitStatistics {
    private String url;
    private Integer numberOfVisits;

    public VisitStatistics(String url, Integer numberOfVisits) {
        this.url = url;
        this.numberOfVisits = numberOfVisits;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

        VisitStatistics that = (VisitStatistics) o;

        return (url != null ? url.equals(that.url) : that.url == null) && (numberOfVisits != null ? numberOfVisits.equals(that.numberOfVisits) : that.numberOfVisits == null);
    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (numberOfVisits != null ? numberOfVisits.hashCode() : 0);
        return result;
    }
}
