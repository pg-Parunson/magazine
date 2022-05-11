package com.sparta.springcore.repository;

import com.sparta.springcore.model.KanbanBucket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KanbanBucketRepository extends JpaRepository<KanbanBucket, String> {
}
