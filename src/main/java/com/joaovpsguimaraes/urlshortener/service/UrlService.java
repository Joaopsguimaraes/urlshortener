package com.joaovpsguimaraes.urlshortener.service;

import java.net.URI;
import java.time.LocalDateTime;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import com.joaovpsguimaraes.urlshortener.dto.ShortenUrlRequest;
import com.joaovpsguimaraes.urlshortener.dto.ShortenUrlResponse;
import com.joaovpsguimaraes.urlshortener.entities.UrlEntity;
import com.joaovpsguimaraes.urlshortener.repository.UrlRepository;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    /**
     * Creates a new shortened URL. The URL is generated using a random alphanumeric string of length between 5 and 10 characters.
     * @param shortenUrlRequest Shorted Url Request containing the original URL
     * @param httpServletRequest HttpServletRequest to get the request URL
     * @return ShortenUrlResponse
     */
    public ShortenUrlResponse shortenUrl(ShortenUrlRequest shortenUrlRequest, HttpServletRequest httpServletRequest) {
        String generatedId;

        // If generated ID already exists, generate a new one until a unique one is found
        do {
            generatedId = RandomStringUtils.randomAlphanumeric(5, 10);
        } while (this.urlRepository.existsById(generatedId));

        UrlEntity urlEntity = new UrlEntity(generatedId, shortenUrlRequest.url(), LocalDateTime.now().plusMinutes(1));

        this.urlRepository.save(urlEntity);

        // Construct the redirect URL using the request URL and the generated ID
        var redirectUrl = httpServletRequest.getRequestURL().toString().replace("shorter-url", generatedId);

        return new ShortenUrlResponse(redirectUrl);
    }

    /**
     * Redirects to the original URL based on the shortened ID.
     * @param id Shortened URL ID
     * @return HttpHeaders
     */
    public HttpHeaders redirect(String id) {
        var urlEntity = this.urlRepository.findById(id);

        if (urlEntity.isEmpty()) {
            return null;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(urlEntity.get().getFullUrl()));

        return headers;
    }

}
