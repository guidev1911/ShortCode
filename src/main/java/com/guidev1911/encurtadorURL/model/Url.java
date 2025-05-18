package com.guidev1911.encurtadorURL.model;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String originalUrl;

    @Column(unique = true)
    private String shortCode;

    public Url() {
    }

    public Url(Long id, String originalUrl, String shortCode) {
        this.id = id;
        this.originalUrl = originalUrl;
        this.shortCode = shortCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Url url = (Url) o;
        return Objects.equals(getId(), url.getId()) && Objects.equals(getOriginalUrl(), url.getOriginalUrl()) && Objects.equals(getShortCode(), url.getShortCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOriginalUrl(), getShortCode());
    }
}
