package com.sparta.springcore.model;

import com.sparta.springcore.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Comment extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "comment_no")
    private Integer commentNo;

    @Column(nullable = false, name = "comment_contents")
    private String commentContents;

    @Column(nullable = false, name = "post_no")
    private Integer postNo;

    @Column(nullable = false, name = "nickname")
    private String nickname;

    public Comment(String commentContents, Integer postNo, String nickname) {
        this.commentContents = commentContents;
        this.postNo = postNo;
        this.nickname = nickname;
    }

    public Comment(CommentRequestDto requestDto) {
        this.commentContents = requestDto.getCommentContents();
        this.postNo = requestDto.getPostNo();
        this.nickname = requestDto.getNickname();
    }

    public void update(CommentRequestDto requestDto) {
        this.commentContents = requestDto.getCommentContents();
    }
}
