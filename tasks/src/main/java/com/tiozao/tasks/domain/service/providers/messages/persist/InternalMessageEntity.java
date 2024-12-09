package com.tiozao.tasks.domain.service.providers.messages.persist;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiozao.tasks.domain.entity.STATUS_INVITE;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity(name= "internal_messages")
public class InternalMessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fromUser;

    private String toUser;

    private String title;

    private String externalId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private STATUS_MESSAGE status;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private TemplateInternalMessageEntity template;

    @Column(name = "attributes", columnDefinition = "TEXT")
    private String attributes;

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


    // Serializa/deserializa o Map para JSON
    public void setAttributesMap(Map<String, String> attributesMap) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            this.attributes = mapper.writeValueAsString(attributesMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getAttributesMap() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(this.attributes, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public TemplateInternalMessageEntity getTemplate() {
        return template;
    }

    public void setTemplate(TemplateInternalMessageEntity template) {
        this.template = template;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public STATUS_MESSAGE getStatus() {
        return status;
    }

    public void setStatus(STATUS_MESSAGE status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
}
