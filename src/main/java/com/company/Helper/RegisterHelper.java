package com.company.Helper;

import com.company.Model.RegisteredURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lzecevic on 6/22/17.
 */

@Component
@Scope("singleton")
public class RegisterHelper {
    private Map<String, RegisteredURL> registeredURLs;

    @Autowired
    private AccountHelper accountHelper;

    @Autowired
    private CredentialsChecker credentialsChecker;

    @Autowired
    private RandomStringGenerator stringGenerator;

    public RegisterHelper() {
        registeredURLs = new HashMap<>();
    }

    public String checkRequestParameters(String auth, RegisteredURL registeredURL) {
        if(!credentialsChecker.checkCredentials(auth.substring(6, auth.length())))
            return "Account ID or password incorrect";

        return registerOrReturnExistingURL(registeredURL).getShortURL();
    }

    private RegisteredURL checkURL(RegisteredURL registeredURL) {
        if(registeredURLs.get(registeredURL.getUrl()) != null)
            return registeredURLs.get(registeredURL.getUrl());
        return null;
    }

    private RegisteredURL registerOrReturnExistingURL(RegisteredURL registeredURL) {
        // provjera da li postoji vec skraceni URL
        if(checkURL(registeredURL) != null) return checkURL(registeredURL);

        // Registrovanje novog URL-a
        if(registeredURL.getRedirectType() == null) registeredURL.setRedirectType(302);
        registeredURL.setShortURL(shorten(registeredURL.getUrl()));
        registeredURLs.put(registeredURL.getUrl(), registeredURL);
        return registeredURL;
    }

    private boolean checkIfSameRandomExists(String random){
        for (RegisteredURL s: registeredURLs.values()) {
            if(s.getShortURL().equals("http://short.com/" + random)) return true;
        }
        return  false;
    }

    private String shorten(String URL){
        // Dodati provjeru da li su dva generisana stringa ista
        // Ako metoda checkIfSameRandomExists vrati true, znaci da postoje
        // te da treba novi random generisati
        return "http://short.com/" + stringGenerator.generateString();
    }
}
