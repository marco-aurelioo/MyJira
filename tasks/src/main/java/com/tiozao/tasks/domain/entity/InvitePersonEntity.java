package com.tiozao.tasks.domain.entity;

import jakarta.persistence.*;

@Entity(name="invite_person")
public class InvitePersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "external_person_id", nullable = false, length = 50)
    private String externalPersonId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;

    @Column(name = "from_person_id", nullable = false, length = 50)
    private String fromPersonId;

    @Column(nullable = false)
    private String template;

    @Column(nullable = true)
    private String response;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private STATUS_INVITE status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExternalPersonId() {
        return externalPersonId;
    }

    public void setExternalPersonId(String externalPersonId) {
        this.externalPersonId = externalPersonId;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public String getFromPersonId() {
        return fromPersonId;
    }

    public void setFromPersonId(String fromPersonId) {
        this.fromPersonId = fromPersonId;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public STATUS_INVITE getStatus() {
        return status;
    }

    public void setStatus(STATUS_INVITE status) {
        this.status = status;
    }
}
