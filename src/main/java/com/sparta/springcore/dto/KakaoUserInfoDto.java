package com.sparta.springcore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor // 모든 멤버 변수 포함한 생성자 자동 생성 (lombok 사용)
public class KakaoUserInfoDto {
    private Long id;
    private String nickname;
    private String email;
    private String profile_image;
}