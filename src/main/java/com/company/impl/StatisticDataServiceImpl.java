package com.company.impl;

import com.company.service.StatisticDataService;
import com.company.model.VisitStatistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticDataServiceImpl implements StatisticDataService {
    private Map<String, List<VisitStatistics>> statistics;

    public StatisticDataServiceImpl() { statistics = new HashMap<>(); }

    public List<VisitStatistics> getStatistics(String accountId) {
        return statistics.get(accountId);
    }

    public void setStatistic(String accountId, String url){
        List<VisitStatistics> listOfVisitStatistics = getListOfVisitStatisticsForUser(accountId);
        VisitStatistics statisticForEdit = getVisitStatisticsForUser(listOfVisitStatistics, url);

        if(statisticForEdit != null) {
            statisticForEdit.setNumberOfVisits(statisticForEdit.getNumberOfVisits() + 1);
        } else {
            listOfVisitStatistics.add(new VisitStatistics(url, 1));
        }
        statistics.put(accountId, listOfVisitStatistics);
    }

    public void putNewAccount(String accountId) {
        statistics.put(accountId, new ArrayList<>());
    }

    private List<VisitStatistics> getListOfVisitStatisticsForUser(String accountId) {
        return statistics.get(accountId);
    }

    private VisitStatistics getVisitStatisticsForUser(List<VisitStatistics> visitStatistics, String url) {
        for (VisitStatistics v: visitStatistics) {
            if(url.equals(v.getUrl())) return v;
        }
        return null;
    }
}
