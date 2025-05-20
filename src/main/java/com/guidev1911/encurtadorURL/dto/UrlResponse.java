package com.guidev1911.encurtadorURL.dto;

import java.time.LocalDateTime;

public class UrlResponse {
    private String shortCode;
    private LocalDateTime expirationDate;

    public UrlResponse(String shortCode, LocalDateTime expirationDate) {
        this.shortCode = shortCode;
        this.expirationDate = expirationDate;
    }

    public String getShortCode() {
        return shortCode;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }
}
