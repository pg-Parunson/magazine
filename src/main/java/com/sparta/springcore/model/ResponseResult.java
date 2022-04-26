package com.sparta.springcore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseResult {
    private String result;
    private String msg;
}

