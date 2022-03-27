package com.tiozao.tasks.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "task_comment")
public class TaskCommentEntity  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "task_comment_id_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private TaskEntity task;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    private String createdAt;

    public TaskCommentEntity build(TaskEntity task, String comment, PersonEntity person, String createdAt) {
        this.task = task;
        this.comment = comment;
        this.person = person;
        this.createdAt = createdAt;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaskEntity getTask() {
        return task;
    }

    public void setTask(TaskEntity task) {
        this.task = task;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
