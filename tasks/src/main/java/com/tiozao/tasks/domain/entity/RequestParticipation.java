package com.tiozao.tasks.domain.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "request_participation")
public class RequestParticipation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requesting_user_id", nullable = false)
    private UserProfile requestingUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approving_user_id", nullable = false)
    private UserProfile approvingUser;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private ParticipationStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Column(columnDefinition = "TEXT")
    private String message;

    @Column(columnDefinition = "TEXT")
    private String response;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public UserProfile getRequestingUser() {
        return requestingUser;
    }

    public void setRequestingUser(UserProfile requestingUser) {
        this.requestingUser = requestingUser;
    }

    public UserProfile getApprovingUser() {
        return approvingUser;
    }

    public void setApprovingUser(UserProfile approvingUser) {
        this.approvingUser = approvingUser;
    }

    public ParticipationStatus getStatus() {
        return status;
    }

    public void setStatus(ParticipationStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}