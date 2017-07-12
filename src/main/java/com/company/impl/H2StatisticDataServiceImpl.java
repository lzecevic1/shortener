package com.company.impl;

import com.company.model.Statistic;
import com.company.repository.StatisticRepository;
import com.company.service.StatisticDataService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class H2StatisticDataServiceImpl implements StatisticDataService {
    @Autowired
    private StatisticRepository statisticRepository;

    public List<Statistic> getStatistics(String accountId) {
        return statisticRepository.findByAccountId(accountId).orElse(Collections.emptyList());
    }

    public void setStatistic(String accountId, String url) {
        Optional<Statistic> statistic = statisticRepository.findByAccountIdAndLongUrl(accountId, url);

        if (statistic.isPresent()) {
            Statistic statisticForEdit = statistic.get();
            statisticForEdit.setNumberOfVisits(statisticForEdit.getNumberOfVisits() + 1);
            statisticRepository.save(statisticForEdit);
        } else {
            statisticRepository.save(new Statistic(accountId, url, 1));
        }
    }
}
