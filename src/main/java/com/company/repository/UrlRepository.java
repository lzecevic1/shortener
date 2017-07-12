package com.company.repository;

import com.company.model.RegisteredUrl;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UrlRepository extends CrudRepository<RegisteredUrl, Long> {
    Optional<RegisteredUrl> findByShortURL(String shortUrl);

    Optional<RegisteredUrl> findByUrl(String url);
}
