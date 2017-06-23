package com.company.Controller;

import com.company.Helper.CredentialsChecker;
import com.company.Helper.StatisticHelper;
import com.company.Model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lzecevic on 6/23/17.
 */
@RestController
@RequestMapping("/visit")
public class VisitController {

    @Autowired
    private StatisticHelper statisticHelper;

    @RequestMapping(value = "/{url}", method = RequestMethod.POST)
    public void register(@PathVariable String url,
                         @RequestBody Account accountId){
        statisticHelper.setStatistic(accountId.getAccountId(), url);
    }
}
