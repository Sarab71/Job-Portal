package com.jobportal.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class HiringPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String position;
    private String company;
    private String location;
    private String requirements;

    private LocalDateTime postedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User postedBy;

    protected HiringPost() {}

    public HiringPost(String position, String company, String location, String requirements, User postedBy) {
        this.position = position;
        this.company = company;
        this.location = location;
        this.requirements = requirements;
        this.postedBy = postedBy;
    }

    @PrePersist
    public void onCreate() {
        postedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getPosition() { return position; }
    public String getCompany() { return company; }
    public String getLocation() { return location; }
    public String getRequirements() { return requirements; }
    public LocalDateTime getPostedAt() { return postedAt; }
    public User getPostedBy() { return postedBy; }
}
