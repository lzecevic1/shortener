package com.company.repository;

import com.company.model.RegisteredUrl;
import org.springframework.data.repository.CrudRepository;

public interface UrlRepository extends CrudRepository<RegisteredUrl, Long> {
    RegisteredUrl findByShortURL(String shortUrl);
    RegisteredUrl findByUrl(String url);
}
