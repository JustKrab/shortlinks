package com.example.shortlinks.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "URL")
public class URLEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String originalUrl;
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String shortUrl;
    @Column(columnDefinition = "VARCHAR(255)")
    private LocalDateTime createTime;
    @Column(columnDefinition = "VARCHAR(255)")
    private int count;
    @Column(columnDefinition = "VARCHAR(255)")
    private LocalDateTime lastRedirect;
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private int liveCycle;
    @Column(columnDefinition = "VARCHAR(255)")
    private String sign;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public URLEntity() {
    }

    public URLEntity(String originalUrl, String shortUrl, LocalDateTime createTime,int liveCycle, int count,String sign) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.createTime = createTime;
        this.count=count;
        this.liveCycle=liveCycle;
        this.sign=sign;
    }


    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime liveCycle) {
        this.createTime = liveCycle;
    }

    public LocalDateTime getLastRedirect() {
        return lastRedirect;
    }

    public void setLastRedirect(LocalDateTime lastRedirect) {
        this.lastRedirect = lastRedirect;
    }

    public int getLiveCycle() {
        return liveCycle;
    }

    public void setLiveCycle(int liveCycle) {
        this.liveCycle = liveCycle;
    }
}
