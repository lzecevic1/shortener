package com.company.controller;

import com.company.model.RegisteredUrl;
import com.company.service.RegisterDataService;
import com.company.service.StatisticDataService;
import com.company.util.CredentialsChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
public class VisitController {

    @Autowired
    private StatisticDataService statisticDataService;

    @Autowired
    private CredentialsChecker credentialsChecker;

    @Autowired
    private RegisterDataService registerDataService;

    @RequestMapping(value = "/{url}")
    public void setVisitStatisticsAndRedirect(@RequestHeader(value = "Authorization") String credentials,
                                              @PathVariable String url,
                                              HttpServletResponse response) throws IOException {
        if(invalidRequestParameters(credentials, url)) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return;
        }

        Optional<String> username = Optional.of(credentialsChecker.getUsernameIfAccountExists(credentials));
        Optional<RegisteredUrl> urlToVisit = registerDataService.getLongURLFromShort(url);
        if(urlToVisit.isPresent()){
            statisticDataService.setStatistic(Optional.of(username).toString(), urlToVisit.get().getUrl());
            redirect(response, urlToVisit);
        }
    }

    private void redirect(HttpServletResponse response, Optional<RegisteredUrl> urlToVisit) {
        response.setHeader("Location", urlToVisit.map(RegisteredUrl::getUrl).toString());
        response.setStatus(Integer.valueOf(urlToVisit.map(RegisteredUrl::getRedirectType).toString()));
    }

    private boolean invalidRequestParameters(String credentials, String url) {
        return url == null || credentials == null;
    }
}
