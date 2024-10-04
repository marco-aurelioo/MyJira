package com.tiozao.tasks.domain.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
abstract class BaseEntity {

    private LocalDateTime createDate;

    private LocalDateTime  modifyDate;


    public LocalDateTime  getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime  createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime  getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDateTime  modifyDate) {
        this.modifyDate = modifyDate;
    }

    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.modifyDate = LocalDateTime.now();
    }

}
