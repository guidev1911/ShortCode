package com.guidev1911.encurtadorURL.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.Objects;

public class UrlRequest {
    @NotBlank(message = "A URL original não pode estar vazia.")
    private String OriginalUrl;
    @Future(message = "A data de expiração não pode estar no passado.")
    private LocalDateTime expirationDate;

    public String getOriginalUrl() {
        return OriginalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        OriginalUrl = originalUrl;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }
}
