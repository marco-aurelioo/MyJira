package com.tiozao.tasks.aplication.controllers.model;

public class ProjectParticipation {
    private Project project;
    private Integer qtdRequest;
    private Integer qtdInvites;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Integer getQtdRequest() {
        return qtdRequest;
    }

    public void setQtdRequest(Integer qtdRequest) {
        this.qtdRequest = qtdRequest;
    }

    public Integer getQtdInvites() {
        return qtdInvites;
    }

    public void setQtdInvites(Integer qtdInvites) {
        this.qtdInvites = qtdInvites;
    }
}
