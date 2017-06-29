package com.company.Controller;

import com.company.Helper.CredentialsChecker;
import com.company.Helper.RegisterHelper;
import com.company.Helper.StatisticHelper;
import com.company.Model.RegisteredURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lzecevic on 6/23/17.
 */
@Controller
public class VisitController {

    @Autowired
    private StatisticHelper statisticHelper;

    @Autowired
    private CredentialsChecker credentialsChecker;

    @Autowired
    private RegisterHelper registerHelper;

    @RequestMapping(value = "/{url}")
    public void setVisitStatisticsAndRedirect(// @RequestHeader(value = "Authorization") String auth,
                                         @PathVariable String url,
                                         HttpServletResponse response) throws IOException {
     //   String username = credentialsChecker.getUsernameIfAccountExists(auth.substring(6, auth.length()));
        RegisteredURL urlToVisit = registerHelper.getRegisteredURLFromShort(url);
     //   if(username != null) statisticHelper.setStatistic(username, urlToVisit.getUrl());

        // Redirect
         response.setHeader("Location", urlToVisit.getUrl());
//        response.sendRedirect(urlToVisit.getUrl());
        response.setStatus(urlToVisit.getRedirectType());
    }
}
