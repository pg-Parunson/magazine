package com.sparta.springcore.service;

import com.sparta.springcore.model.IsLike;
import com.sparta.springcore.model.Post;
import com.sparta.springcore.model.User;
import com.sparta.springcore.repository.IsLikeRepository;
import com.sparta.springcore.repository.PostRepository;
import com.sparta.springcore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional // DB에 반영되야 한다~~~ 라고 알려줌
@RequiredArgsConstructor // final 로 선언된 녀석의 생성자를 자동으로 만들어준다.
@Service // 얘는 서비스다~~
public class IsLikeService {

    private final IsLikeRepository isLikeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 좋아요 & 좋아요 취소
    public void plusLike(Integer postNo, String username) {
        // 해당 게시글이 유효한 게시글인지 조회
        Post post = postRepository.findById(postNo).orElseThrow(
                () -> new NullPointerException("해당 게시글이 존재하지 않습니다.")
        );
        // 유저 아이디를 키로 유저 객체 조회
        Optional<User> user = userRepository.findByUsername(username);
        // 유저가 존재하면
        if (user.isPresent()) {
            // 유저 넘버 획득
            Integer userNo = user.get().getUserNo();
            // 좋아요 테이블에 해당 포스트 번호 & 좋아요 누른 유저로 이루어진 데이터가 없으면 (좋아요 처리)
            if (isLikeRepository.findByPostNoAndUserNo(postNo, userNo) == null) {
                // 좋아요 테이블에 해당 포스트 번호 & 좋아요 누른 유저 번호로 1행 추가
                IsLike isLike = new IsLike(postNo, userNo);
                isLikeRepository.save(isLike);
                // 좋아요 눌린 해당 포스트의 좋아요 컬럼에 +1
                post.setLikes(post.getLikes() + 1);

            // 좋아요 테이블에 해당 포스트 번호 & 좋아요 누른 유저로 이루어진 데이터가 있으면 (좋아요 취소처리)
            } else {
                // 좋아요 테이블에 해당 포스트 번호 & 좋아요 누른 유저 번호로 생성된 행 삭제
                isLikeRepository.deleteByPostNoAndUserNo(postNo, userNo);
                // 좋아요 눌린 해당 포스트의 좋아요 컬럼에 -1
                post.setLikes(post.getLikes() - 1);
            }
        }
    }
}

