package com.sparta.springcore.dto;

import lombok.Getter;

@Getter
public class BucketInfoDto {

    // 소속 프로젝트 ID
    private String pjId;

    // 버킷 ID
    private String kbbId;

    // 버킷 제목
    private String title;

    // 배치 순서
    private int position;

}