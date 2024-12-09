package com.tiozao.tasks.aplication.controllers.response;

import com.tiozao.tasks.domain.entity.ProjectEntity;

public class ProjectResponse {

    private String id;
    private String nome;
    private String description;

    private String alias;

    public ProjectResponse(String id, String projectName, String description, String alias) {
        this.id = id;
        this.nome = projectName;
        this.description = description;
        this.alias = alias;
    }

    public ProjectResponse(ProjectEntity project) {
        this.id =project.getExternalId();
        this.nome = project.getProjectName();
        this.alias = project.getProjectAlias();
        this.description = project.getDescription();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
