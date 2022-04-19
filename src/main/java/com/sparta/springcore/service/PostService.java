package com.sparta.springcore.service;

import com.sparta.springcore.dto.PostRequestDto;
import com.sparta.springcore.model.Post;
import com.sparta.springcore.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional // DB에 반영되야 한다~~~ 라고 알려줌
@RequiredArgsConstructor // final 로 선언된 녀석의 생성자를 자동으로 만들어준다.
@Service // 얘는 서비스다~~
public class PostService {

    private final PostRepository postRepository;

    // 게시글 전체 조회
    public List<Post> readPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    // 특정 게시글 조회
    public Post getPost(Integer postNo) {
        Post post = postRepository.getById(postNo);
        // 조회수 1 증가
        post.setViews(post.getViews() + 1);
        return post;
    }

    // 새 게시글 등록
    public void createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        // 좋아요 0 초기화
        post.setLikes(0);
        // 조회수 0 초기화
        post.setViews(0);
        postRepository.save(post);
    }

    // 게시글 삭제
    public void deletePost(Integer postNo) {
        postRepository.deleteById(postNo);
    }

    // 게시글 수정
    public void updatePost(Integer postNo, PostRequestDto requestDto) {
        Post post = postRepository.findById(postNo).orElseThrow(
                () -> new NullPointerException("해당 게시글이 존재하지 않습니다.")
        );

        post.update(requestDto);
    }

}
