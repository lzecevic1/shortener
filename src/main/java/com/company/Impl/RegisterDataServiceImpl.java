package com.company.Impl;

import com.company.Interface.RegisterDataService;
import com.company.Model.LongURL;
import com.company.Model.RegisteredURL;
import com.company.Util.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class RegisterDataServiceImpl implements RegisterDataService {
    private Map<String, RegisteredURL> registeredURLs;

    @Autowired
    private RandomStringGenerator stringGenerator;

    public RegisterDataServiceImpl() {
        registeredURLs = new HashMap<>();
    }

    public String getShortURL(LongURL longURL){
        RegisteredURL registeredURL = getRegisteredURL(longURL);
        if(registeredURL == null) return registerURL(longURL);
        return registeredURL.getShortURL();
    }

    public RegisteredURL getLongURLFromShort(String shortURL){
        return registeredURLs.get(shortURL);
    }

    private String registerURL(LongURL longURL) {
        String newShortUrl = generateNewShortUrl();
        if(longURL.getRedirectType() == null) longURL.setRedirectType(302);
        RegisteredURL newURL = new RegisteredURL(longURL.getUrl(),
                                                 longURL.getRedirectType(),
                                                 newShortUrl);
        registeredURLs.put(newShortUrl, newURL);
        return newShortUrl;
    }

    private RegisteredURL getRegisteredURL(LongURL urlToRegister) {
        for (RegisteredURL registeredURL: registeredURLs.values()) {
            if(urlToRegister.getUrl().equals(registeredURL.getUrl())){
                return registeredURL;
            }
        }
        return null;
    }

    private String generateNewShortUrl(){
        String newShort = stringGenerator.generateString();
        while(registeredURLs.get(newShort) != null) newShort = stringGenerator.generateString();
        return newShort;
    }

}
