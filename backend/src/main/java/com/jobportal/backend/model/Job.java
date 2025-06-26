package com.jobportal.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String skills;

    private LocalDateTime postedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User postedBy;

    protected Job() {}

    public Job(String title, String description, String skills, User postedBy) {
        this.title = title;
        this.description = description;
        this.skills = skills;
        this.postedBy = postedBy;
    }

    @PrePersist
    public void prePersist() {
        postedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getSkills() { return skills; }
    public LocalDateTime getPostedAt() { return postedAt; }
    public User getPostedBy() { return postedBy; }
}
