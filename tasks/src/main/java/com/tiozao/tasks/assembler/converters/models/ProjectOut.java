package com.tiozao.tasks.assembler.converters.models;

import com.tiozao.tasks.aplication.controllers.response.ProjectResponse;
import com.tiozao.tasks.domain.entity.ProjectEntity;

public class ProjectOut implements ObjectsOut{

    private ProjectEntity projectEntity;

    private ProjectResponse projectResponse;

    public ProjectOut(ProjectResponse projectResponse) {
        this.projectResponse = projectResponse;
    }

    public ProjectOut(ProjectEntity projectEntity) {
        this.projectEntity = projectEntity;
        this.projectResponse =  new ProjectResponse(
                projectEntity.getProjectName(),
                projectEntity.getProjectAlias(),
                projectEntity.getDescription()
        );
    }

    public ProjectEntity getProjectEntity() {
        return projectEntity;
    }

    public ProjectResponse getProjectResponse() {
        return projectResponse;
    }
}
