package com.guidev1911.encurtadorURL.dto;

import java.time.LocalDateTime;

public class ApiErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String message;

    public ApiErrorResponse(int status, String message, LocalDateTime now) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
    }
    public ApiErrorResponse(int status, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}