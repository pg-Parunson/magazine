package com.sparta.springcore.service;

import com.sparta.springcore.dto.CommentRequestDto;
import com.sparta.springcore.model.Comment;
import com.sparta.springcore.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional // DB에 반영되야 한다~~~ 라고 알려줌
@RequiredArgsConstructor // final로 선언된 녀석의 생성자를 자동으로 만들어준다.
@Service // 얘는 서비스다~~
public class CommentService {

    private final CommentRepository commentRepository;

    // 댓글 전체 조회
    public List<Comment> readComment() {
        return commentRepository.findAllByOrderByCreatedAtDesc();
    }

    // 댓글 작성
    public void createComment(CommentRequestDto requestDto) {
        Comment comment = new Comment(requestDto);
        commentRepository.save(comment);
    }

    // 댓글 삭제
    public void deleteComment(Integer commentNo) {
        commentRepository.deleteById(commentNo);
    }

    // 댓글 내용 수정
    public void update(Integer commentNo, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentNo).orElseThrow(
                () -> new NullPointerException("해당 댓글이 존재하지 않습니다.")
        );
        comment.update(requestDto);
    }
}