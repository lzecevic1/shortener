package com.company.Helper;

import com.company.Model.RegisteredURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by lzecevic on 6/22/17.
 */

@Component
@Scope("singleton")
public class RegisterHelper {
    private Map<String, RegisteredURL> registeredURLs;

    @Autowired
    private CredentialsChecker credentialsChecker;

    @Autowired
    private RandomStringGenerator stringGenerator;

    public RegisterHelper() {
        registeredURLs = new HashMap<>();
    }

    public RegisteredURL getShortURLIfAuthenticated(String auth, RegisteredURL registeredURL){
        if(checkRequestParameters(auth)) {
            return registerOrReturnExistingURL(registeredURL);
        }
        return null;
    }

    public RegisteredURL getRegisteredURLFromShort(String shortURL){
        return registeredURLs.get(shortURL);
    }

    private Boolean checkRequestParameters(String auth) {
        return credentialsChecker.decodeAndcheckCredentials(auth.substring(6, auth.length()));
    }

    private RegisteredURL getRegisteredURL(RegisteredURL urlToRegister) {
        for (RegisteredURL registeredURL: registeredURLs.values()) {
            if(urlToRegister.getUrl().equals(registeredURL.getUrl())){
                return registeredURL;
            }
        }
        return null;
    }

    private RegisteredURL registerOrReturnExistingURL(RegisteredURL registeredURL) {
        // provjera da li postoji vec skraceni URL
        if(getRegisteredURL(registeredURL) != null) return getRegisteredURL(registeredURL);

        // Registrovanje novog URL-a
        if(registeredURL.getRedirectType() == null) registeredURL.setRedirectType(302);
        registeredURL.setShortURL(shorten(registeredURL.getUrl()));
        registeredURLs.put(registeredURL.getShortURL(), registeredURL);
        return registeredURL;
    }

    private String shorten(String URL){
        return stringGenerator.generateString();
    }
}
