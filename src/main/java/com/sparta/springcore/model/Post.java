package com.sparta.springcore.model;

import com.sparta.springcore.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Post extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "post_no")
    private Integer postNo;

    @Column(nullable = false, name = "post_title")
    private String postTitle;

    @Column(nullable = false, name = "post_contents", length = 2000)
    private String postContents;

    @Column(nullable = false, name = "nickname")
    private String nickname;

    @Column(name = "images")
    private String images;

    @Column(nullable = false, name = "likes")
    private Integer likes;

    @Column(nullable = false, name = "views")
    private Integer views;

    // 1: 중앙, 2: 좌, 3: 우
    @Column(nullable = false, name = "layout")
    private Integer layout;


    public Post(String postTitle, String postContents, String nickname, String images, Integer likes, Integer views, Integer layout) {
        this.postTitle = postTitle;
        this.postContents = postContents;
        this.nickname = nickname;
        this.images = images;
        this.likes = likes;
        this.views = views;
        this.layout = layout;
    }

    public Post(PostRequestDto requestDto) {
        this.postTitle = requestDto.getPostTitle();
        this.postContents = requestDto.getPostContents();
        this.nickname = requestDto.getNickname();
        this.images = requestDto.getImages();
        this.likes = requestDto.getLikes();
        this.views = requestDto.getViews();
        this.layout = requestDto.getLayout();
    }

    public void update(PostRequestDto requestDto) {
        this.postTitle = requestDto.getPostTitle();
        this.postContents = requestDto.getPostContents();
        this.images = requestDto.getImages();
        this.layout = requestDto.getLayout();
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public void setLayout(Integer layout) {
        this.layout = layout;
    }
}
