package com.example.shortlinks.services;

import com.example.shortlinks.entity.URLEntity;

import javax.transaction.Transactional;
import java.util.List;

public interface URLService {

    void saveUrl(URLEntity url);

    URLEntity findByOriginal(String url);

    List<URLEntity> findAll();

    URLEntity find(String url);

    @Transactional
    void remove(String url);
}
