package com.guidev1911.encurtadorURL.controller;

import com.guidev1911.encurtadorURL.dto.UrlRequest;
import com.guidev1911.encurtadorURL.dto.UrlResponse;
import com.guidev1911.encurtadorURL.model.Url;
import com.guidev1911.encurtadorURL.service.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name = "URL", description = "Endpoints para criar URLs e consultar dados da URL ")
public class UrlController {

    @Autowired
    private UrlService service;

    @Operation(summary = "Cria uma URL encurtada - data de expiração é opcional")
    @ApiResponse(
            responseCode = "201",
            description = "A URL foi encurtada com sucesso"
    )
    @PostMapping("/shorten")
    public ResponseEntity<Object> shorten(@RequestBody UrlRequest request) {
        Url url = service.createShortUrl(request.getOriginalUrl(), request.getExpirationDate());

        Map<String, String> response = new HashMap<>();
        response.put("shortCode", "localhost:8080/"+url.getShortCode());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Redireciona o link")
    @ApiResponse(
            responseCode = "200",
            description = "Redirecionamento com sucesso"
    )
    @ApiResponse(
            responseCode = "404",
            description = "URL não encontrada ou expirada"
    )
    @GetMapping("/{shortCode}")
    public ResponseEntity<?> redirect(@PathVariable String shortCode) {
        try {
            String originalUrl = service.getOriginalUrl(shortCode);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create(originalUrl))
                    .build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
    @Operation(summary = "exibe dados da url encurtada")
    @ApiResponse(
            responseCode = "200",
            description = "URL foi encontrada e os dados foram exibidos"
    )
    @ApiResponse(
            responseCode = "404",
            description = "URL não encontrada ou expirada"
    )
    @GetMapping("/stats/{shortCode}")
    public ResponseEntity<?> getStats(@PathVariable String shortCode) {
        try {
            UrlResponse response = service.getUrlStats(shortCode);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}