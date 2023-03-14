package com.mjc.school.service.dto;

public class NewsUpdateDto {
    long authorId;
    String title;
    String content;
    long id;

    public NewsUpdateDto(long id, String title, String content, long authorId) {
        this.id = id;
        this.authorId = authorId;
        this.title = title;
        this.content = content;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
