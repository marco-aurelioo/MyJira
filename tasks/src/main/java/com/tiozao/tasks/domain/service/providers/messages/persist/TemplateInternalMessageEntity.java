package com.tiozao.tasks.domain.service.providers.messages.persist;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "templates_internal_messages")
public class TemplateInternalMessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String externalId;

    private String owner;

    private String template;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
}
