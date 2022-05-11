package com.sparta.springcore.repository;

import com.sparta.springcore.model.KanbanCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface KanbanCardRepository extends JpaRepository<KanbanCard, String> {
    List<KanbanCard> findAllByKbcId(UUID kbcId);
}
