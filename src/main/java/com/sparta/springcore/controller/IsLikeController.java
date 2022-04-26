package com.sparta.springcore.controller;

import com.sparta.springcore.model.ResponseResult;
import com.sparta.springcore.service.IsLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class IsLikeController {

    private final IsLikeService isLikeService;

    // 좋아요 처리
    @GetMapping("/api/posts/{postNo}/like")
    public ResponseEntity<ResponseResult> plusLike(@PathVariable Integer postNo, HttpSession httpSession) {
        if (httpSession == null) {
            return new ResponseEntity<>(new ResponseResult("fail", null), HttpStatus.BAD_REQUEST);
        }
        Integer userNo = (Integer) httpSession.getAttribute("userNo");
        isLikeService.plusLike(postNo, userNo);
        return new ResponseEntity<>(new ResponseResult("success", null), HttpStatus.OK);
    }
}

