package com.guidev1911.encurtadorURL.service;

import com.guidev1911.encurtadorURL.model.Url;
import com.guidev1911.encurtadorURL.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class UrlService {

    @Autowired
    private UrlRepository repository;

    public Url createShortUrl(String originalUrl) {
        String shortCode = UUID.randomUUID().toString().substring(0, 6);
        Url url = new Url(null, originalUrl, shortCode);
        return repository.save(url);
    }

    public Optional<String> getOriginalUrl(String shortCode) {
        return repository.findByShortCode(shortCode)
                .map(Url::getOriginalUrl);
    }
}
