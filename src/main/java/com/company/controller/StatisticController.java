package com.company.controller;

import com.company.model.Statistic;
import com.company.service.StatisticDataService;
import com.company.util.CredentialsChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/statistic")
public class StatisticController {
    @Autowired
    private StatisticDataService statisticDataService;

    @Autowired
    private CredentialsChecker credentialsChecker;

    @RequestMapping(value = "/{AccountId}", method = RequestMethod.POST)
    public List<Statistic> getStatistic(@RequestHeader(value = "Authorization") String credentials,
                                        @PathVariable String AccountId) throws Exception {

        if (!authenticate(credentials, AccountId)) return Collections.emptyList();
        return statisticDataService.getStatistics(AccountId);
    }

    private boolean authenticate(String credentials, String accountId) throws Exception {
        if (credentials == null || accountId == null) return false;
        return credentialsChecker.authenticate(credentials);
    }
}
