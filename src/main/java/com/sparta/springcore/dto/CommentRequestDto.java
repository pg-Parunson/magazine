package com.sparta.springcore.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String commentContents;
    private Integer postNo;
    private String nickname;
}

