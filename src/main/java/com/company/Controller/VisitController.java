package com.company.Controller;

import com.company.Helper.CredentialsChecker;
import com.company.Helper.RegisterHelper;
import com.company.Helper.StatisticHelper;
import com.company.Model.Account;
import com.company.Model.RegisteredURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lzecevic on 6/23/17.
 */
@RestController
public class VisitController {

    @Autowired
    private StatisticHelper statisticHelper;

    @Autowired
    private CredentialsChecker credentialsChecker;

    @Autowired
    private RegisterHelper registerHelper;

    @RequestMapping(value = "/{url}")
    public void visitURLAndSetStatistics(@RequestHeader(value = "Authorization") String auth,
                                         @PathVariable String url,
                                         HttpServletResponse response) throws IOException {
        String username = credentialsChecker.getUsernameIfAccountExists(auth.substring(6, auth.length()));
        if(username != null) statisticHelper.setStatistic(username, url);

        RegisteredURL registeredURL = registerHelper.getRegisteredURLFromShort(url);

        response.setStatus(registeredURL.getRedirectType());
        response.sendRedirect(registeredURL.getUrl());
    }
}
