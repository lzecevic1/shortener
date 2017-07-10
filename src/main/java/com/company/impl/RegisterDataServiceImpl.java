package com.company.impl;

import com.company.model.LongUrl;
import com.company.model.RegisteredUrl;
import com.company.service.RegisterDataService;
import com.company.util.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class RegisterDataServiceImpl {
    private Map<String, RegisteredUrl> registeredURLs;

    @Autowired
    private RandomStringGenerator stringGenerator;

    public RegisterDataServiceImpl() {
        registeredURLs = new HashMap<>();
    }

    public RegisterDataServiceImpl(RandomStringGenerator randomStringGenerator) {
        stringGenerator = randomStringGenerator;
        registeredURLs = new HashMap<>();
    }

    public String getShortURL(LongUrl longUrl) {
        RegisteredUrl registeredUrl = getRegisteredURL(longUrl);
        if (registeredUrl == null) return registerURL(longUrl);
        return registeredUrl.getShortURL();
    }

    public RegisteredUrl getLongURLFromShort(String shortURL) {
        return registeredURLs.get(shortURL);
    }

    private String registerURL(LongUrl longUrl) {
        String newShortUrl = generateNewShortUrl();
        if (longUrl.getRedirectType() == null) longUrl.setRedirectType(302);
        RegisteredUrl newURL = new RegisteredUrl(longUrl.getUrl(),
                longUrl.getRedirectType(),
                newShortUrl);
        registeredURLs.put(newShortUrl, newURL);
        return newShortUrl;
    }

    private RegisteredUrl getRegisteredURL(LongUrl urlToRegister) {
        for (RegisteredUrl registeredUrl : registeredURLs.values()) {
            if (urlToRegister.getUrl().equals(registeredUrl.getUrl())) {
                return registeredUrl;
            }
        }
        return null;
    }

    private String generateNewShortUrl() {
        String newShort = stringGenerator.generateString();
        while (registeredURLs.get(newShort) != null) newShort = stringGenerator.generateString();
        return newShort;
    }
}
