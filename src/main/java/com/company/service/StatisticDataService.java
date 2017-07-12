package com.company.service;

import com.company.model.Statistic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StatisticDataService {
    List<Statistic> getStatistics(String accountId);

    void setStatistic(String accountId, String url);
}
