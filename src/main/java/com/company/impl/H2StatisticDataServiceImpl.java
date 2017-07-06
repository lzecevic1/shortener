package com.company.impl;

import com.company.model.Statistic;
import com.company.repository.FullAccountRepository;
import com.company.repository.UrlRepository;
import com.company.repository.StatisticRepository;
import com.company.service.StatisticDataService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class H2StatisticDataServiceImpl implements StatisticDataService {
    @Autowired
    private StatisticRepository statisticRepository;
    @Autowired
    private FullAccountRepository fullAccountRepository;
    @Autowired
    private UrlRepository urlRepository;

    public List<Statistic> getStatistics(String accountId) {
        Long userId = getUserId(accountId);
        return statisticRepository.findByUserId(userId);
    }

    public void setStatistic(String accountId, String url) {
        Long userId = getUserId(accountId);
        Long urlId = getUrlId(url);
        Statistic statistic = statisticRepository.findByUserIdAndUrlId(userId, urlId);
        if(statistic != null) statistic.setNumberOfVisits(statistic.getNumberOfVisits() + 1);
        else statistic = new Statistic(userId, urlId, 1);

        statisticRepository.save(statistic);
    }

    private Long getUrlId(String url) {
        return urlRepository.findByUrl(url).getId();
    }

    private Long getUserId(String accountId) {
        return fullAccountRepository.findByAccountId(accountId).getId();
    }
}
