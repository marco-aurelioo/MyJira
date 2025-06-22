package com.tiozao.tasks.domain.views;

import com.tiozao.tasks.domain.entity.Project;

public class ProjectRequestParticipation {

    private Project project;
    private Integer qtdPendingRequests;
    private Integer qtdPendingInvites;

    public ProjectRequestParticipation(Project project, Integer qtdPendingRequests, Integer qtdPendingInvites) {
        this.project = project;
        this.qtdPendingRequests = qtdPendingRequests;
        this.qtdPendingInvites = qtdPendingInvites;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Integer getQtdPendingRequests() {
        return qtdPendingRequests;
    }

    public void setQtdPendingRequests(Integer qtdPendingRequests) {
        this.qtdPendingRequests = qtdPendingRequests;
    }

    public Integer getQtdPendingInvites() {
        return qtdPendingInvites;
    }

    public void setQtdPendingInvites(Integer qtdPendingInvites) {
        this.qtdPendingInvites = qtdPendingInvites;
    }


}
