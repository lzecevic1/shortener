package com.company.service;

import com.company.model.LongUrl;
import com.company.model.RegisteredUrl;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface RegisterDataService {
    Optional<RegisteredUrl> getLongURLFromShort(String shortURL);
    Optional<String> getShortURL(LongUrl longUrl);
    void registerUrl(LongUrl longUrl);
}
