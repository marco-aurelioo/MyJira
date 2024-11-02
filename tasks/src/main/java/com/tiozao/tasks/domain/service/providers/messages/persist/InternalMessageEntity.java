package com.tiozao.tasks.domain.service.providers.messages.persist;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity(name= "internal_messages")
public class InternalMessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String from;

    private String to;

    private String title;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private TemplateInternalMessageEntity template;

    @Lob
    @Column(name = "attributes", columnDefinition = "BLOB")
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
    private Boolean read;

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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
