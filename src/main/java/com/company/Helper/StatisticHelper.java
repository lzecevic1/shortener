package com.company.Helper;

import com.company.Model.VisitStatistics;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by lzecevic on 6/23/17.
 */

@Component
@Scope("singleton")
public class StatisticHelper {
    private Map<String, List<VisitStatistics>> statistics;

    public StatisticHelper() { statistics = new HashMap<>(); }

    public List<VisitStatistics> getStatistics(String accountId) {
        return statistics.get(accountId);
    }

    // Vraca true ako je statistika uspjesno postavljena, false u suprotnom
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

    void putNewAccount(String accountId) {
        statistics.put(accountId, new ArrayList<>());
    }

    private List<VisitStatistics> getListOfVisitStatisticsForUser(String accountId) {
        return statistics.get(accountId);
    }

    private VisitStatistics getVisitStatisticsForUser(List<VisitStatistics> visitStatistics, String url) {
        for (VisitStatistics v: visitStatistics) {
            if(url.equals(v.getUrl())) return v;
        }
        return null; // Vraca null ukoliko nije pronadjena statistika za odredjeni url
    }
}
