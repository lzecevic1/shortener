package com.company.Controller;

import com.company.Interface.StatisticDataService;
import com.company.Util.CredentialsChecker;
import com.company.Model.VisitStatistics;
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
    public List<VisitStatistics> getStatistic(@RequestHeader(value = "Authorization") String auth,
                                              @PathVariable String AccountId){
        if(auth == null || AccountId == null) return null;
        if(credentialsChecker.decodeAndCheckCredentials(auth)){
            return statisticDataService.getStatistics(AccountId);
        }

        return null;
    }

}
