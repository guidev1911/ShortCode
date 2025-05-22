package com.guidev1911.encurtadorURL.controller.swagger;
import com.guidev1911.encurtadorURL.dto.UrlRequest;
import com.guidev1911.encurtadorURL.dto.UrlResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface UrlControllerDoc {

    @Operation(summary = "Cria uma URL encurtada")
    @ApiResponse(responseCode = "200", description = "URL encurtada criada com sucesso")
    @PostMapping("/shorten")
    public ResponseEntity<UrlResponse> shorten(@RequestBody UrlRequest request);

    @Operation(summary = "Redireciona a URL encurtada para a original")
    @GetMapping("/{shortCode}")
    public ResponseEntity<?> redirect(@PathVariable String shortCode);

    @Operation(summary = "Exibe todas as informações sobre a URL")
    @GetMapping("/stats/{shortCode}")
    public ResponseEntity<?> getStats(@PathVariable String shortCode);
}