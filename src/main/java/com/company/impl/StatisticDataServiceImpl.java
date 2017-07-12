package com.company.impl;

import com.company.model.Statistic;
import com.company.service.StatisticDataService;

import java.util.*;

public class StatisticDataServiceImpl implements StatisticDataService {
    private Map<String, List<Statistic>> statistics;

    public StatisticDataServiceImpl() {
        statistics = new HashMap<>();
    }

    public List<Statistic> getStatistics(String accountId) {
        List<Statistic> statisticList = statistics.get(accountId);
        if (statisticList == null) {
            return Collections.emptyList();
        }
        return statisticList;
    }

    public void setStatistic(String accountId, String url) {
        if (!statistics.containsKey(accountId)) {
            setStatisticForNewUser(accountId, url);
        } else {
            setStatisticForExistingUser(accountId, url);
        }
    }

    private void setStatisticForNewUser(String accountId, String url) {
        statistics.put(accountId, new ArrayList<>());
        statistics.get(accountId).add(new Statistic(accountId, url, 1));
    }

    private void setStatisticForExistingUser(String accountId, String url) {
        List<Statistic> userStatistic = statistics.get(accountId);
        Optional<Statistic> statisticForEdit = getStatisticForEdit(userStatistic, url);

        if (!statisticForEdit.isPresent()) {
            userStatistic.add(new Statistic(accountId, url, 1));
        } else {
            Statistic newStatistic = statisticForEdit.get();
            newStatistic.setNumberOfVisits(newStatistic.getNumberOfVisits() + 1);
            userStatistic.add(newStatistic);
        }
    }

    private Optional<Statistic> getStatisticForEdit(List<Statistic> userStatistic, String url) {
        return userStatistic.stream()
                .filter(s -> url.equals(s.getLongUrl()))
                .findFirst();
    }
}
