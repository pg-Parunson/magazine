package com.sparta.springcore.repository;

import com.sparta.springcore.model.IsLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IsLikeRepository extends JpaRepository<IsLike, Integer> {
    IsLike findByPostNoAndUserNo(Integer postNo, Integer userNo);
    void deleteByPostNoAndUserNo(Integer postNo, Integer userNo);
}
