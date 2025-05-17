package com.joaovpsguimaraes.urlshortener.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.joaovpsguimaraes.urlshortener.entities.UrlEntity;

public interface UrlRepository extends MongoRepository<UrlEntity, String> {
}
