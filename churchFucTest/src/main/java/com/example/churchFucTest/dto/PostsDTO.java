package com.example.churchFucTest.dto;

import com.example.churchFucTest.domain.Posts;

public class PostsDTO {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsDTO(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
