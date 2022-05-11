package com.sparta.springcore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Project extends BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(updatable = false, nullable = false)
    private UUID pjId;

    @Column(columnDefinition = "TEXT")
    private String thumbnail;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false, length = 20)
    private String summary;

    @Column(nullable = false)
    private String inviteCode;

//    @JsonIgnore
//    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
//    private List<Folder> forders = new ArrayList<>();
//
//    @JsonIgnore
//    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
//    private List<Document> documents = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<KanbanBucket> kanbanBuckets = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<KanbanCard> kanbanCards = new ArrayList<>();

//    @JsonIgnore
//    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
//    private List<ProjectMember> projectMembers = new ArrayList<>();
//
//    @Builder
//    public Project(String thumbnail, String title, String summary, String inviteCode, List<Folder> forders,
//                   List<Document> documents, List<KanbanBucket> kanbanBuckets, List<KanbanCard> kanbanCards,
//                   List<ProjectMember> projectMembers) {
//        this.thumbnail = thumbnail;
//        this.title = title;
//        this.summary = summary;
//        this.inviteCode = inviteCode;
//        this.forders = forders;
//        this.documents = documents;
//        this.kanbanBuckets = kanbanBuckets;
//        this.kanbanCards = kanbanCards;
//        this.projectMembers = projectMembers;
//    }

    public Project(String thumbnail, String title, String summary, String inviteCode, List<KanbanBucket> kanbanBuckets,
                   List<KanbanCard> kanbanCards) {
        this.thumbnail = thumbnail;
        this.title = title;
        this.summary = summary;
        this.inviteCode = inviteCode;
        this.kanbanBuckets = kanbanBuckets;
        this.kanbanCards = kanbanCards;
    }
}
