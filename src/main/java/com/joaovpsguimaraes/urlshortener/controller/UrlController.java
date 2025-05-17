package com.joaovpsguimaraes.urlshortener.controller;

import com.joaovpsguimaraes.urlshortener.dto.ShortenUrlRequest;
import com.joaovpsguimaraes.urlshortener.dto.ShortenUrlResponse;
import com.joaovpsguimaraes.urlshortener.service.UrlService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    /**
     * Shortens a URL and returns the shortened URL.
     * @param request Shorten Url Request containing the original URL
     * @param httpServletRequest HttpServletRequest to get the request URL
     * @return ResponseEntity<ShortenUrlResponse>
     */
    @PostMapping(value = "/shorten-url")
    public ResponseEntity<ShortenUrlResponse> shorterUrl(@RequestBody ShortenUrlRequest request,
            HttpServletRequest httpServletRequest) {

        var shortenUrlResponse = this.urlService.shortenUrl(request, httpServletRequest);
        return ResponseEntity.ok(shortenUrlResponse);
    }

    /**
     * Redirects to the original URL based on the shortened URL ID.
     * @param id Shortened URL ID
     * @return ResponseEntity<Void>
     */
    @GetMapping("{id}")
    public ResponseEntity<Void> redirect(@PathVariable("id") String id) {
        var urlLocation = this.urlService.redirect(id);

        if (urlLocation == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.FOUND).headers(urlLocation).build();
    }
}
