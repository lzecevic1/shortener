package com.company.service;

import com.company.model.LongUrl;
import com.company.model.RegisteredUrl;
import org.springframework.stereotype.Service;

@Service
public interface RegisterDataService {
    RegisteredUrl getLongURLFromShort(String shortURL);
    String getShortURL(LongUrl longUrl);
}
