package com.sparta.springcore.repository;

import com.sparta.springcore.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByOrderByCreatedAtDesc();
    void deleteAllByPostNo(Integer postNo);
}
