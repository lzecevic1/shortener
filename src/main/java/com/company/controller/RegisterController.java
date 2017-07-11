package com.company.controller;

import com.company.model.LongUrl;
import com.company.service.RegisterDataService;
import com.company.util.CredentialsChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private static String URL = "http://localhost:8080/";

    @Autowired
    private RegisterDataService registerDataService;
    @Autowired
    private CredentialsChecker credentialsChecker;

    @RequestMapping(method = RequestMethod.POST)
    public String registerURL(@RequestHeader(value = "Authorization") String credentials, @RequestBody LongUrl longUrl) throws Exception {

        Optional<String> message = checkRequestParams(credentials, longUrl);
        if (message.isPresent()) return message.get();

        try {
            registerDataService.registerUrl(longUrl);
        } catch (IllegalArgumentException exception) {
            return "Saving to database failed. " + exception.getMessage();
        }
        return URL + registerDataService.getShortURL(longUrl);
    }

    private Optional<String> checkRequestParams(String credentials, LongUrl longUrl) throws Exception {
        if (isLongUrlInvalid(longUrl)) {
            return Optional.of("URL missing or incorrect!");
        }
        if (credentials == null) {
            return Optional.of("Authorization missing!");
        }
        if (!credentialsChecker.authenticate(credentials)) {
            return Optional.of("Incorrect username or password!");
        }
        return Optional.empty();
    }

    private boolean isLongUrlInvalid(LongUrl longUrl) {
        return longUrl == null || longUrl.getUrl() == null;
    }
}
