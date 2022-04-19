package com.sparta.springcore.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private String postTitle;
    private String postContents;
    private String nickname;
    private String images;
    private Integer likes;
    private Integer views;
}
