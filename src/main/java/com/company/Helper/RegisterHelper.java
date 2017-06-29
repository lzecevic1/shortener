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
    private CredentialsChecker credentialsChecker;

    @Autowired
    private RandomStringGenerator stringGenerator;

    public RegisterHelper() {
        registeredURLs = new HashMap<>();
    }

    public RegisteredURL getShortURLIfAuthenticated(String auth, RegisteredURL registeredURL){
        if(isUserAuthenticated(auth)) {
            return registerOrReturnExisting(registeredURL);
        }
        return null;
    }

    public RegisteredURL getRegisteredURLFromShort(String shortURL){
        return registeredURLs.get(shortURL);
    }

    private Boolean isUserAuthenticated(String auth) {
        return credentialsChecker.decodeAndCheckCredentials(auth.substring(6, auth.length()));
    }

    private RegisteredURL getRegisteredURL(RegisteredURL urlToRegister) {
        // Ako je dugi url urlToRegister isti kao url vec postojeceg url-a, vraca se registrovani url
        // U suprotnom se vraca null
        for (RegisteredURL registeredURL: registeredURLs.values()) {
            if(urlToRegister.getUrl().equals(registeredURL.getUrl())){
                return registeredURL;
            }
        }
        return null;
    }

    // Metoda provjerava da li je neki url vec skracen
    // Ukoliko jeste, vraca objekat sa tim url-om i njegovim kratkim url-om
    // Ukoliko nije, generise novi objekat i stavlja ga u mapu
    private RegisteredURL registerOrReturnExisting(RegisteredURL registeredURL) {

        if(getRegisteredURL(registeredURL) != null) return getRegisteredURL(registeredURL);

        // Registrovanje novog URL-a
        if(registeredURL.getRedirectType() == null) registeredURL.setRedirectType(302);
        registeredURL.setShortURL(getNewShortUrl());
        registeredURLs.put(registeredURL.getShortURL(), registeredURL);
        return registeredURL;
    }

    // Metoda provjerava da li vec postoji isti generisani random string za kratki url
    // Ukoliko postoji, generise novi
    private String getNewShortUrl(){
        String newShort = stringGenerator.generateString();
        while(registeredURLs.get(newShort) != null) newShort = stringGenerator.generateString();
        return newShort;
    }
}
