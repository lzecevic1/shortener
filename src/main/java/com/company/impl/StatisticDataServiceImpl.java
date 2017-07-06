package com.company.impl;

import com.company.model.Statistic;
import com.company.service.StatisticDataService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticDataServiceImpl implements StatisticDataService{
    private Map<String, List<Statistic>> statistics;

    public StatisticDataServiceImpl() {
        statistics = new HashMap<>();
    }

    public List<Statistic> getStatistics(String accountId) {
        return statistics.get(accountId);
    }

    public void setStatistic(String accountId, String url) {
        if(!statistics.containsKey(accountId)) setStatisticForNewUser(accountId, url);
         else setStatisticForExistingUser(accountId, url);
    }

    private void setStatisticForNewUser(String accountId, String url) {
        statistics.put(accountId, new ArrayList<>());
        statistics.get(accountId).add(new Statistic(accountId, url, 1));
    }

    private void setStatisticForExistingUser(String accountId, String url) {
        List<Statistic> userStatistic = statistics.get(accountId);
        Statistic statisticForEdit = getStatisticForEdit(userStatistic, url);
        if(statisticForEdit == null) userStatistic.add(new Statistic(accountId, url, 1));
        else {
            statisticForEdit.setNumberOfVisits(statisticForEdit.getNumberOfVisits() + 1);
            userStatistic.add(statisticForEdit);
        }
    }

    private Statistic getStatisticForEdit(List<Statistic> userStatistic, String url) {
        for (Statistic s : userStatistic) {
            if(url.equals(s.getLongUrl())){
                return s;
            }
        }
        return null;
    }

}
