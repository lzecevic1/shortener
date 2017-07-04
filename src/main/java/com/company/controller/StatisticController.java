package com.company.controller;

import com.company.service.StatisticDataService;
import com.company.util.CredentialsChecker;
import com.company.model.VisitStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statistic")
public class StatisticController {
    @Autowired
    private StatisticDataService statisticDataService;

    @Autowired
    private CredentialsChecker credentialsChecker;

    @RequestMapping(value = "/{AccountId}", method = RequestMethod.POST)
    public List<VisitStatistics> getStatistic(@RequestHeader(value = "Authorization") String credentials,
                                              @PathVariable String AccountId){
        if(invalidRequestParameters(credentials, AccountId)) return null;
        if(credentialsChecker.authenticate(credentials)) return statisticDataService.getStatistics(AccountId);
        return null;
    }

    private boolean invalidRequestParameters(String credentials, String AccountId) {
        return credentials == null || AccountId == null;
    }

}
