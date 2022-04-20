package com.sparta.springcore.controller;

import com.sparta.springcore.service.IsLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://3.38.106.41/")
public class IsLikeController {

    private final IsLikeService isLikeService;

    // 좋아요 처리
    @GetMapping("/api/posts/{postNo}/like")
    public void plusLike(@PathVariable Integer postNo, HttpSession httpSession) {
        Integer userNo = (Integer) httpSession.getAttribute("userNo");
        isLikeService.plusLike(postNo, userNo);
    }
}

