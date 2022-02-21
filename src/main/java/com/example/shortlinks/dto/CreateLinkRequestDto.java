package com.example.shortlinks.dto;


import java.time.LocalDateTime;

public class CreateLinkRequestDto {


    private String originalLink;

    private LocalDateTime createTime;

    private int liveCycle;

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public int getLiveCycle() {
        return liveCycle;
    }

    public void setLiveCycle(int liveCycle) {
        this.liveCycle = liveCycle;
    }

    public String getOriginalLink() {
        return originalLink;
    }

    public void setOriginalLink(String originalLink) {
        this.originalLink = originalLink;
    }
}
