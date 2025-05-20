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

        if (originalUrl == null || originalUrl.isBlank()) {
            throw new IllegalArgumentException("A URL original não pode estar vazia.");
        }

        LocalDateTime now = LocalDateTime.now();

        if (expirationDate == null) {
            expirationDate = now.plusDays(1); // padrão
        } else if (expirationDate.isBefore(now)) {
            throw new IllegalArgumentException("A data de expiração não pode estar no passado.");
        } else if (expirationDate.isAfter(now.plusDays(7))) {
            throw new IllegalArgumentException("A data de expiração não pode exceder 7 dias.");
        }

        String shortCode = UUID.randomUUID().toString().substring(0, 8);
        Url url = new Url(null, originalUrl, shortCode, expirationDate);
        return repository.save(url);
    }

    public Optional<String> getOriginalUrl(String shortCode) {
        return repository.findByShortCode(shortCode)
                .filter(url -> url.getExpirationDate().isAfter(LocalDateTime.now()))
                .map(Url::getOriginalUrl)
                .orElseThrow(() -> new IllegalArgumentException("URL não encontrada ou expirada.")).describeConstable();
    }
}
