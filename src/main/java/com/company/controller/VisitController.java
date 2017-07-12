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
                                              HttpServletResponse response) throws Exception {
        if (invalidRequestParameters(credentials, url)) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return;
        }

        Optional<String> username = credentialsChecker.getUsernameIfAccountExists(credentials);
        Optional<RegisteredUrl> urlToVisit = registerDataService.getLongURLFromShort(url);
        RegisteredUrl urlForRedirect = urlToVisit.orElseThrow(Exception::new);
        String usernameForRedirect = username.orElseThrow(Exception::new);

        setStatistic(usernameForRedirect, urlForRedirect.getUrl());
        redirect(response, urlForRedirect);
    }

    private void setStatistic(String username, String urlForRedirect) throws Exception {
        statisticDataService.setStatistic(username, urlForRedirect);
    }

    private void redirect(HttpServletResponse response, RegisteredUrl urlToVisit) {
        response.setHeader("Location", urlToVisit.getUrl());
        response.setStatus(urlToVisit.getRedirectType());
    }

    private boolean invalidRequestParameters(String credentials, String url) {
        return url == null || credentials == null;
    }
}
