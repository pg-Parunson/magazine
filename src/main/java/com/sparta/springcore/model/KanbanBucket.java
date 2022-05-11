package com.sparta.springcore.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class KanbanBucket extends BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(updatable = false, nullable = false)
    private UUID kbbId;

    // 프로젝트 번호
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pjId")
    private Project project;

    // 버킷 제목
    @Column(nullable = false)
    @ColumnDefault("'untitle'")
    private String title = "untitle";

    // 배치순서
    @Column(nullable = false)
    @ColumnDefault("0")
    private int position = 0;

    @OneToMany(mappedBy = "kanbanBucket", cascade = CascadeType.ALL)
    private List<KanbanCard> cards = new ArrayList<>();

    @Builder
    public KanbanBucket(Project project, String title, int position, List<KanbanCard> cards) {
        this.project = project;
        this.title = title;
        this.position = position;
        this.cards = cards;
    }
}
