package com.guidev1911.encurtadorURL.service;

import com.guidev1911.encurtadorURL.model.Url;
import com.guidev1911.encurtadorURL.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UrlService {

    @Autowired
    private UrlRepository repository;

    public Url createShortUrl(String originalUrl,  LocalDateTime expirationDate) {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime finalExpiration;

        if (expirationDate == null) {
            finalExpiration = now.plusDays(1);
        } else {
            if (expirationDate.isBefore(now)) {
                throw new IllegalArgumentException("Data de expiração não pode estar no passado.");
            }

            LocalDateTime maxExpiration = now.plusDays(7);
            finalExpiration = expirationDate.isAfter(maxExpiration) ? maxExpiration : expirationDate;
        }
        String shortCode;
        do {
            shortCode = UUID.randomUUID().toString().substring(0, 6);
        } while (repository.findByShortCode(shortCode).isPresent());

        Url url = new Url(null, originalUrl, shortCode, finalExpiration);
        return repository.save(url);
    }

    public Optional<String> getOriginalUrl(String shortCode) {
        return repository.findByShortCode(shortCode)
                .filter(url -> url.getExpirationDate() == null || url.getExpirationDate().isAfter(LocalDateTime.now()))
                .map(Url::getOriginalUrl);
    }
}
