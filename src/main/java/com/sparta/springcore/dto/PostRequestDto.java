package com.sparta.springcore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.springcore.model.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequestDto {
    private String postTitle;
    private String postContents;
    private String nickname;
    private String images;
    private Integer likes;
    private Integer views;
    private Integer layout;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedAt;

    public PostRequestDto(Post post) {
        this.postTitle = post.getPostTitle();
        this.postContents = post.getPostContents();
        this.nickname = post.getNickname();
        this.images = post.getImages();
        this.likes = post.getLikes();
        this.views = post.getViews();
        this.layout = post.getLayout();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }
}
