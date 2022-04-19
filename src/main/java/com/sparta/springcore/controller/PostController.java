package com.sparta.springcore.controller;

import com.sparta.springcore.dto.PostRequestDto;
import com.sparta.springcore.model.Post;
import com.sparta.springcore.security.UserDetailsImpl;
import com.sparta.springcore.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 전체 조회
    @GetMapping("/api/posts")
    public List<Post> readPost() {
        return postService.readPosts();
    }

    // 게시글 조회 및 상세페이지 이동
    @RequestMapping("/api/posts/{postNo}")
    public ModelAndView getPost(@PathVariable Integer postNo, Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Post post = postService.getPost(postNo);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(post);
        modelAndView.setViewName("detail");

        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("nickname", userDetails.getNickName());

        return modelAndView;
    }

    // 게시글 등록
    @PostMapping("/api/posts")
    public void createPost(@RequestBody PostRequestDto requestDto) {
        postService.createPost(requestDto);
    }

    // 게시글 삭제
    @DeleteMapping("/api/posts/{postNo}")
    public void deletePost(@PathVariable Integer postNo) {
        postService.deletePost(postNo);
    }

    // 게시글 수정
    @PutMapping("/api/posts/{postNo}")
    public Integer updatePost(@PathVariable Integer postNo, @RequestBody PostRequestDto requestDto) {
        postService.updatePost(postNo, requestDto);
        return postNo;
    }

}