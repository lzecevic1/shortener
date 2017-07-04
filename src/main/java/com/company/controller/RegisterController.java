package com.company.controller;

import com.company.service.RegisterDataService;
import com.company.model.LongURL;
import com.company.util.CredentialsChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private static String URL = "http://localhost:8080/";

    @Autowired
    private RegisterDataService registerDataService;
    @Autowired
    private CredentialsChecker credentialsChecker;


    @RequestMapping(method = RequestMethod.POST)
    public String registerURL(@RequestHeader(value = "Authorization") String credentials, @RequestBody LongURL longURL){

        String message = checkRequestParams(credentials, longURL);
        if(message != null) return message;
        return URL + registerDataService.getShortURL(longURL);
    }

    private String checkRequestParams(String credentials, LongURL longURL) {
        if(isLongUrlInvalid(longURL)) return "URL missing or incorrect!";
        if(credentials == null) return "Authorization missing!";
        if(!credentialsChecker.authenticate(credentials)) return "Incorrect username or password!";
        return null;
    }

    private boolean isLongUrlInvalid(LongURL longURL) {
        return longURL == null || longURL.getUrl() == null;
    }
}
