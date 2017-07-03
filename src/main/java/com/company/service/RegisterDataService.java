package com.company.service;

import com.company.model.LongURL;
import com.company.model.RegisteredURL;
import org.springframework.stereotype.Service;

@Service
public interface RegisterDataService {
    RegisteredURL getLongURLFromShort(String shortURL);
    String getShortURL(LongURL longURL);
}
