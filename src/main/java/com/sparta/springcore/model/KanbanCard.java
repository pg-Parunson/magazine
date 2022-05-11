package com.sparta.springcore.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class KanbanCard extends BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(updatable = false, nullable = false)
    private UUID kbcId;

    // 버킷 번호
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kbbId")
    private KanbanBucket kanbanBucket;

    // 프로젝트 번호
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pjId")
    private Project project;

    // 카드 제목
    @Column(nullable = false, length = 20)
    @ColumnDefault("'untitle'")
    private String title;

    // 본문
    @Column(nullable = false, columnDefinition = "TEXT")
    private String contents;

    // 배치순서
    @Column(nullable = false)
    @ColumnDefault("0")
    private int position = 0;

    @Builder
    public KanbanCard(KanbanBucket kanbanBucket, Project project, String title, String contents, int position) {
        this.kanbanBucket = kanbanBucket;
        this.project = project;
        this.title = title;
        this.contents = contents;
        this.position = position;
    }
}

