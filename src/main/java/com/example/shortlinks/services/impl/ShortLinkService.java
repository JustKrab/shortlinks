package com.example.shortlinks.services.impl;

import com.example.shortlinks.entity.URLEntity;
import com.example.shortlinks.services.ShortlinkService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ShortLinkService implements ShortlinkService {

    @Autowired
    private URLServiceImpl urlServiceImpl;

    @Autowired
    private GeneratorServiceImpl generatorServiceImpl;


    public String create(String originalLink, int liveCycle) throws NoSuchAlgorithmException {
        URLEntity link;
        SortedMap<String, Object> map = new TreeMap<>();
        String shortLink = generatorServiceImpl.generate(5);
        if (originalLink != null) {
            map.put("originalLink", shortLink);
            map.put("shortLink", originalLink);
            map.put("liveCycle", liveCycle);
            String mapToString = map.keySet().stream()
                    .map(key -> key + "=" + map.get(key))
                    .collect(Collectors.joining("&"));
            String sign = mapToString.trim()+"&" +
                    UUID.randomUUID();
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(sign.getBytes(StandardCharsets.UTF_8));
            String sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
            link = new URLEntity(originalLink, shortLink, LocalDateTime.now(), liveCycle, 0, sha1);
            urlServiceImpl.saveUrl(link);
            return shortLink;
        } else {
            return "errorpage";
        }
    }

    public ResponseEntity<?> redirect(String u) throws Exception {
        String originalLink = getOriginalLink(u);
        LocalDateTime now = LocalDateTime.now();
        URLEntity urlEntity = urlServiceImpl.find(u);
        if (Strings.isEmpty(originalLink)) {
            return ResponseEntity.notFound().build();
        }
        if (Duration.between(urlEntity.getCreateTime(), now).toHours() > urlEntity.getLiveCycle()) {
            removeUrl(u);
            return ResponseEntity.notFound().build();
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(new URI(originalLink));
        urlEntity.setCount(urlEntity.getCount() + 1);
        urlEntity.setLastRedirect(now);
        urlServiceImpl.saveUrl(urlEntity);
        return new ResponseEntity<>(httpHeaders, HttpStatus.PERMANENT_REDIRECT);
    }

    public String getOriginalLink(String shortlink) {
        String originalURL = urlServiceImpl.find(shortlink).getOriginalUrl();
        return Strings.isEmpty(originalURL) ? null : originalURL;
    }

    public void removeUrl(String u) {
        urlServiceImpl.remove(u);
    }
}
