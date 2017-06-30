package com.company.Interface;

import com.company.Model.VisitStatistics;

import java.util.List;

public interface StatisticDataService {
    List<VisitStatistics> getStatistics(String accountId);
    void setStatistic(String accountId, String url);
    void putNewAccount(String accountId);
}
