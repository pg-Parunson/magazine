package com.sparta.springcore.controller;

import com.sparta.springcore.dto.UserRequestDto;
import com.sparta.springcore.model.ResponseResult;
import com.sparta.springcore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원 로그인 페이지
    @GetMapping("/api/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/api/register")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/api/register")
    public ResponseEntity<ResponseResult> registerUser(@RequestBody @Valid UserRequestDto requestDto, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(new ResponseResult("fail", "회원가입 실패"), HttpStatus.OK);
        }
        userService.registerUser(requestDto);
        return new ResponseEntity<>(new ResponseResult("success", "회원가입 성공"), HttpStatus.OK);
    }
}