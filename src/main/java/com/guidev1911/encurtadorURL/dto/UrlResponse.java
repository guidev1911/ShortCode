package com.guidev1911.encurtadorURL.dto;

import java.time.LocalDateTime;

public class UrlResponse {
    private String shortCode;
    private String originalUrl;
    private int clickCount;
    private LocalDateTime createdAt;
    private LocalDateTime expirationDate;

    public UrlResponse(String shortCode, String originalUrl, int clickCount, LocalDateTime createdAt, LocalDateTime expirationDate) {
        this.shortCode = shortCode;
        this.originalUrl = originalUrl;
        this.clickCount = clickCount;
        this.createdAt = createdAt;
        this.expirationDate = expirationDate;
    }

    public String getShortCode() {
        return shortCode;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public int getClickCount() {
        return clickCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }
}

