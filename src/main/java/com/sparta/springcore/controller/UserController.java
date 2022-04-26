package com.sparta.springcore.controller;

import com.sparta.springcore.dto.UserRequestDto;
import com.sparta.springcore.login.LoginForm;
import com.sparta.springcore.login.SessionConst;
import com.sparta.springcore.model.ResponseResult;
import com.sparta.springcore.model.User;
import com.sparta.springcore.service.UserService;
import com.sparta.springcore.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;
    private final SessionManager sessionManager;

    @Autowired
    public UserController(UserService userService, SessionManager sessionManager) {
        this.userService = userService;
        this.sessionManager = sessionManager;
    }

    // 회원 로그인 페이지
    @GetMapping("/api/login")
    public String loginForm() {
        return "login";
    }

    // 로그인
    @PostMapping("/api/login")
    public ResponseEntity<ResponseResult> login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new ResponseResult("fail", "에러 발생"), HttpStatus.BAD_REQUEST);
        }

        User loginMember = userService.login(form.getUsername(), form.getPassword());
        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return new ResponseEntity<>(new ResponseResult("fail", "아이디 또는 비밀번호가 맞지 않습니다."), HttpStatus.OK);
        }

        // 로그인 성공 처리
        // 세션이 있으면 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession(true);
        // 세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        response.setHeader("username", loginMember.getUsername());

        return new ResponseEntity<>(new ResponseResult("success", "로그인 성공"), HttpStatus.OK);
    }

    // 로그아웃
    @PostMapping("/api/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    private void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
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