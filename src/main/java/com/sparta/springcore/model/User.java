package com.sparta.springcore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class User extends Timestamped {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_no")
    private Integer userNo;

    // nullable: null 허용 여부
    // unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false, unique = true, name = "user_id")
    private String username;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    // null 쌉가능 (일반 폼 가입 유저 위함)
    // 중복 허용 안함
    @Column(unique = true)
    private Long kakaoId;

    @Column
    private String profile_image;

    // 일반 유저를 위한 생성자
    public User(String username, String nickname, String password, String email) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.kakaoId = null;
        this.profile_image = null;
    }

    // 카카오 회원가입한 사람을 위한 생성자
    public User(String nickname, String password, String email, Long kakaoId, String profile_image) {
        this.username = nickname;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.kakaoId = kakaoId;
        this.profile_image = profile_image;
    }
}