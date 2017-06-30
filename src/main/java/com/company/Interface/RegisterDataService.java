package com.company.Interface;

import com.company.Model.LongURL;
import com.company.Model.RegisteredURL;

public interface RegisterDataService {
    RegisteredURL getLongURLFromShort(String shortURL);
    String getShortURL(LongURL longURL);
}
