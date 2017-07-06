package com.company.impl;

import com.company.model.Statistic;
import com.company.repository.StatisticRepository;
import com.company.service.StatisticDataService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class H2StatisticDataServiceImpl implements StatisticDataService {
    @Autowired
    private StatisticRepository statisticRepository;

    public List<Statistic> getStatistics(String accountId) {
        return statisticRepository.findByAccountId(accountId);
    }

    public void setStatistic(String accountId, String url) {
        Statistic statistic = statisticRepository.findByAccountIdAndLongUrl(accountId, url);
        if(statistic != null) statistic.setNumberOfVisits(statistic.getNumberOfVisits() + 1);
        else statistic = new Statistic(accountId, url, 1);

        statisticRepository.save(statistic);
    }
}
