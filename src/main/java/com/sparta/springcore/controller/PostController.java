package com.sparta.springcore.controller;

import com.sparta.springcore.dto.PostRequestDto;
import com.sparta.springcore.model.Post;
import com.sparta.springcore.model.ResponseResult;
import com.sparta.springcore.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PostController {

    private final PostService postService;

    // 게시글 전체 조회
    @GetMapping("/api/posts")
    public List<Post> readPost() {
        return postService.readPosts();
    }

    // 게시글 조회 및 상세페이지 이동
    @GetMapping("/api/posts/{postNo}")
    public PostRequestDto getPost(@PathVariable Integer postNo) {
        return postService.getPost(postNo);
    }

    // 게시글 등록
    @PostMapping("/api/posts")
    public ResponseEntity<ResponseResult> createPost(@RequestBody PostRequestDto requestDto, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(new ResponseResult("fail", null), HttpStatus.OK);
        }
        postService.createPost(requestDto);
        return new ResponseEntity<>(new ResponseResult("success", null), HttpStatus.OK);
    }

    // 게시글 삭제
    @DeleteMapping("/api/posts/{postNo}")
    public ResponseEntity<ResponseResult> deletePost(@PathVariable Integer postNo) {
        postService.deletePost(postNo);
        return new ResponseEntity<>(new ResponseResult("success", null), HttpStatus.OK);
    }

    // 게시글 수정
    @PutMapping("/api/posts/{postNo}")
    public Integer updatePost(@PathVariable Integer postNo, @RequestBody PostRequestDto requestDto) {
        postService.updatePost(postNo, requestDto);
        return postNo;
    }

    // 게시글 레이아웃 (중앙, 좌, 우) 설정
    @PutMapping("/api/posts/{postNo}/{layout}")
    public Integer setLayout(@PathVariable Integer postNo, @PathVariable Integer layout) {
        postService.setLayout(postNo, layout);
        return postNo;
    }
}