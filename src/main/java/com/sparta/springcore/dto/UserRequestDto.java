package com.sparta.springcore.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Setter
@Getter
public class UserRequestDto {

//    @NotBlank(message = "Please fill out this field.")
    private String username;

//    @NotBlank(message = "Please fill out this field.")
    private String nickname;

//    @NotBlank(message = "Please fill out this field.")
//    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{4,16}", message = "비밀번호는 4~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;

//    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "Invalid email format.")
//    @NotBlank(message = "Please fill out this field.")
    private String email;
}
