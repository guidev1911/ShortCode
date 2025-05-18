package com.guidev1911.encurtadorURL.controller;

import com.guidev1911.encurtadorURL.model.Url;
import com.guidev1911.encurtadorURL.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class UrlController {

    @Autowired
    private UrlService service;

    @PostMapping("/shorten")
    public ResponseEntity<String> shorten(@RequestParam String url) {
        Url newUrl = service.createShortUrl(url);
        return ResponseEntity.ok("http://localhost:8080/" + newUrl.getShortCode());
    }

    @GetMapping("/{shortCode}")
    public RedirectView redirect(@PathVariable String shortCode) {
        return service.getOriginalUrl(shortCode)
                .map(RedirectView::new)
                .orElseGet(() -> {
                    RedirectView view = new RedirectView("/not-found");
                    view.setStatusCode(org.springframework.http.HttpStatus.NOT_FOUND);
                    return view;
                });
    }

    @GetMapping("/not-found")
    public ResponseEntity<String> notFound() {
        return ResponseEntity.status(404).body("URL não encontrada.");
    }
}