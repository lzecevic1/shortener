package com.company.impl;

import com.company.model.LongUrl;
import com.company.model.RegisteredUrl;
import com.company.service.RegisterDataService;
import com.company.util.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RegisterDataServiceImpl implements RegisterDataService {
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

    public Optional<RegisteredUrl> getLongURLFromShort(String shortURL) {
        return Optional.ofNullable(registeredURLs.get(shortURL));
    }

    public Optional<String> getShortURL(LongUrl longUrl) {
        Optional<RegisteredUrl> registeredUrl = getRegisteredURL(longUrl);
        return registeredUrl.flatMap(registeredUrl1 -> Optional.of(registeredUrl1.getShortURL()));
    }

    public void registerUrl(LongUrl longUrl) {
        if (longUrl.getRedirectType() == null) {
            longUrl.setRedirectType(302);
        }
        String newShortUrl = generateNewShortUrl();
        RegisteredUrl newURL = new RegisteredUrl(longUrl.getUrl(),
                longUrl.getRedirectType(),
                newShortUrl);
        registeredURLs.put(newShortUrl, newURL);
    }

    private Optional<RegisteredUrl> getRegisteredURL(LongUrl urlToRegister) {
        return registeredURLs.values().stream()
                .filter(registeredUrl -> urlToRegister.getUrl().equals(registeredUrl.getUrl()))
                .findAny();
    }

    private String generateNewShortUrl() {
        String newShort = stringGenerator.generateString();
        while (registeredURLs.get(newShort) != null) newShort = stringGenerator.generateString();
        return newShort;
    }
}
