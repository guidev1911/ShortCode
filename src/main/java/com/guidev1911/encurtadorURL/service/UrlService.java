package com.guidev1911.encurtadorURL.service;

import com.guidev1911.encurtadorURL.dto.UrlResponse;
import com.guidev1911.encurtadorURL.exceptions.*;
import com.guidev1911.encurtadorURL.model.Url;
import com.guidev1911.encurtadorURL.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;


@Service
public class UrlService {

    @Autowired
    private UrlRepository repository;

    @Autowired
    private UrlServiceValidation validation;

    public Url createShortUrl(String originalUrl, LocalDateTime expirationDate) {
        if (originalUrl == null || originalUrl.isBlank()) {
            throw new EmptyUrlException("URL original vazia");
        }

        if (!validation.isValidUrl(originalUrl)) {
            throw new InvalidUrlFormatException("A URL fornecida é inválida ou potencialmente maliciosa.");
        }

        LocalDateTime now = LocalDateTime.now();

        if (expirationDate == null) {
            expirationDate = now.plusDays(1);
        } else if (expirationDate.isBefore(now)) {
            throw new ExpirationDateInPastException("A data de expiração não pode estar no passado.");
        } else if (expirationDate.isAfter(now.plusDays(7))) {
            throw new ExpirationDateExceedsLimitException("A data de expiração não pode exceder 7 dias.");
        }

        String shortCode = validation.generateUniqueShortCode();

        LocalDateTime lctn = LocalDateTime.now();
        Url url = new Url(null, originalUrl, shortCode, expirationDate, lctn);
        return repository.save(url);
    }

    @Transactional
    public String getOriginalUrl(String shortCode) {
        if (shortCode == null || shortCode.isBlank()) {
            throw new EmptyUrlException("URL encurtada vazia");
        }

        Url url = repository.findByShortCode(shortCode)
                .filter(u -> u.getExpirationDate().isAfter(LocalDateTime.now()))
                .orElseThrow(() -> new UrlNotFoundException("URL não encontrada ou expirada."));

        url.setClickCount(url.getClickCount() + 1);
        repository.save(url);

        return url.getOriginalUrl();
    }

    public UrlResponse getUrlStats(String shortCode) {
        if (shortCode == null || shortCode.isBlank()) {
            throw new EmptyUrlException("URL encurtada vazia");
        }

        Url url = repository.findByShortCode(shortCode)
                .orElseThrow(() -> new UrlNotFoundException("URL não encontrada."));

        return new UrlResponse(
                url.getShortCode(),
                url.getOriginalUrl(),
                url.getClickCount(),
                url.getCreatedAt(),
                url.getExpirationDate()
        );
    }
}
