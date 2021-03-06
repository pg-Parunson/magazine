package com.sparta.springcore.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class BucketDto {

    // 버킷 ID
    private String kbbId;

    // 버킷 제목
    private String title;

    // 해당 버킷 내 카드 정보
    private List<CardInfoDto> cards;

}
