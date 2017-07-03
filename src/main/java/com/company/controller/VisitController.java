package com.company.controller;

import com.company.service.RegisterDataService;
import com.company.service.StatisticDataService;
import com.company.util.CredentialsChecker;
import com.company.model.RegisteredURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class VisitController {

    @Autowired
    private StatisticDataService statisticDataService;

    @Autowired
    private CredentialsChecker credentialsChecker;

    @Autowired
    private RegisterDataService registerDataService;

    @RequestMapping(value = "/{url}")
    public void setVisitStatisticsAndRedirect(@RequestHeader(value = "Authorization") String auth,
                                              @PathVariable String url,
                                              HttpServletResponse response) throws IOException {
        if(url == null || auth == null) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return;
        }

        String username = credentialsChecker.getUsernameIfAccountExists(auth);
        RegisteredURL urlToVisit = registerDataService.getLongURLFromShort(url);
        if(username != null) {
            statisticDataService.setStatistic(username, urlToVisit.getUrl());
            // Redirect
            response.setHeader("Location", urlToVisit.getUrl());
            response.setStatus(urlToVisit.getRedirectType());
        }
    }
}
