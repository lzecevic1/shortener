package com.company.impl;

import com.company.model.LongUrl;
import com.company.model.RegisteredUrl;
import com.company.repository.UrlRepository;
import com.company.service.RegisterDataService;
import com.company.util.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;

public class H2RegisterDataServiceImpl implements RegisterDataService {
    @Autowired
    private UrlRepository urlRepository;
    @Autowired
    private RandomStringGenerator stringGenerator;

    public RegisteredUrl getLongURLFromShort(String shortURL) {
        return urlRepository.findByShortURL(shortURL);
    }

    public String getShortURL(LongUrl longUrl) {
        String url = longUrl.getUrl();
        RegisteredUrl registeredUrl = urlRepository.findByUrl(url);
        if(registeredUrl == null){
            return registerUrl(longUrl, url);
        }
        return registeredUrl.getShortURL();
    }

    private String registerUrl(LongUrl longUrl, String url) {
        String shortUrl = stringGenerator.generateString();
        if(longUrl.getRedirectType() == null) longUrl.setRedirectType(302);
        urlRepository.save(new RegisteredUrl(url, longUrl.getRedirectType(), shortUrl));
        return shortUrl;
    }
}
