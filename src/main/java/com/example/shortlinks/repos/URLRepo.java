package com.example.shortlinks.repos;

import com.example.shortlinks.entity.URLEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface URLRepo extends JpaRepository<URLEntity, String> {

    URLEntity findByShortUrl(String url);

    URLEntity findByOriginalUrl(String originalUrl);

    void removeByShortUrl(String url);

}
