package com.company.impl;

import com.company.model.LongUrl;
import com.company.model.RegisteredUrl;
import com.company.repository.UrlRepository;
import com.company.service.RegisterDataService;
import com.company.util.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class H2RegisterDataServiceImpl implements RegisterDataService {
    @Autowired
    private UrlRepository urlRepository;
    @Autowired
    private RandomStringGenerator stringGenerator;

    public Optional<RegisteredUrl> getLongURLFromShort(String shortURL) {
        return urlRepository.findByShortURL(shortURL);
    }

    public Optional<String> getShortURL(LongUrl longUrl) {
        String url = longUrl.getUrl();
        Optional<RegisteredUrl> registeredUrl = urlRepository.findByUrl(url);

        return registeredUrl.flatMap(registeredUrl1 -> Optional.of(registeredUrl1.getShortURL()));
    }

    public void registerUrl(LongUrl longUrl) {
        String newPassword = stringGenerator.generateString();
        urlRepository.save(generateNewUrl(longUrl, newPassword));
    }

    private RegisteredUrl generateNewUrl(LongUrl longUrl, String password) {
        return new RegisteredUrl(longUrl.getUrl(),
                longUrl.getRedirectType(),
                password);
    }
}
