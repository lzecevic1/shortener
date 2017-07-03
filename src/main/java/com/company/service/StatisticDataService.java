package com.company.service;

import com.company.model.VisitStatistics;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StatisticDataService {
    List<VisitStatistics> getStatistics(String accountId);
    void setStatistic(String accountId, String url);
    void putNewAccount(String accountId);
}
