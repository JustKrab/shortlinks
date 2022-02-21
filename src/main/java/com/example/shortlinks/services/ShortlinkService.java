package com.example.shortlinks.services;

import org.springframework.http.ResponseEntity;

import java.security.NoSuchAlgorithmException;

public interface ShortlinkService {

    String create(String originalLink, int liveCycle) throws NoSuchAlgorithmException;

    ResponseEntity<?> redirect(String u) throws Exception;

    String getOriginalLink(String shortlink);

    void removeUrl(String u);
}
