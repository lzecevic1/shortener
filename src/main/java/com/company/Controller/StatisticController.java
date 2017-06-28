package com.company.Controller;

import com.company.Helper.CredentialsChecker;
import com.company.Helper.StatisticHelper;
import com.company.Model.VisitStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lzecevic on 6/23/17.
 */

@RestController
@RequestMapping("/statistic")
public class StatisticController {
    @Autowired
    private StatisticHelper statisticHelper;

    @Autowired
    private CredentialsChecker credentialsChecker;

    @RequestMapping(value = "/{AccountId}")
    public List<VisitStatistics> getStatistic(@RequestHeader(value = "Authorization") String auth,
                                              @PathVariable String AccountId){
        if(credentialsChecker.decodeAndcheckCredentials(auth.substring(6, auth.length())))
            return statisticHelper.getStatistics(AccountId);

        return null;
    }

}
