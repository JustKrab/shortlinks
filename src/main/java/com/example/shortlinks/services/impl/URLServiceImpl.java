package com.example.shortlinks.services.impl;

import com.example.shortlinks.entity.URLEntity;
import com.example.shortlinks.repos.URLRepo;
import com.example.shortlinks.services.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class URLServiceImpl implements URLService {

    @Autowired
    private URLRepo repo;

    public void saveUrl(URLEntity url) {
        repo.save(url);
    }


    public URLEntity findByOriginal(String url) {
        return repo.findByOriginalUrl(url);
    }

    public List<URLEntity> findAll() {
        return repo.findAll();
    }

    public URLEntity find(String url) {
        return repo.findByShortUrl(url);
    }

    @Transactional
    public void remove(String url) {
        repo.removeByShortUrl(url);
    }
}
