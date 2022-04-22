package com.sparta.springcore.controller;

import com.sparta.springcore.dto.CommentRequestDto;
import com.sparta.springcore.model.Comment;
import com.sparta.springcore.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // 댓글 전체 조회
    @GetMapping("/api/comments")
    public List<Comment> readComment() {
        return commentService.readComment();
    }

    // 댓글 등록
    @PostMapping("/api/comments")
    public void createComment(@RequestBody CommentRequestDto requestDto) {
        commentService.createComment(requestDto);
    }

    // 댓글 삭제
    @DeleteMapping("/api/comments/{commentNo}")
    public void deleteComment(@PathVariable Integer commentNo) {
        commentService.deleteComment(commentNo);
    }

    // 댓글 수정
    @PutMapping("/api/comments/{commentNo}")
    public void updatePost(@PathVariable Integer commentNo, @RequestBody CommentRequestDto requestDto) {
        commentService.update(commentNo, requestDto);
    }
}
