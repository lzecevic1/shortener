package com.company.controller;

import com.company.service.RegisterDataService;
import com.company.model.LongURL;
import com.company.util.CredentialsChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private static String START_OF_URL = "http://localhost:8080/";

    @Autowired
    private RegisterDataService registerDataService;
    @Autowired
    private CredentialsChecker credentialsChecker;

    @RequestMapping(method = RequestMethod.POST)
    public String registerURL(@RequestHeader(value = "Authorization") String auth,
                              @RequestBody LongURL longURL){

        String message = checkRequestParams(auth, longURL);
        if(message != null) return message;
        return START_OF_URL + registerDataService.getShortURL(longURL);
    }

    private String checkRequestParams(String auth, LongURL longURL) {
        if(longURL == null || longURL.getUrl() == null) return "URL missing or incorrect!";
        if(auth == null) return "Authorization missing!";
        if(!credentialsChecker.decodeAndCheckCredentials(auth)) return "Incorrect username or password!";
        return null;
    }
}
