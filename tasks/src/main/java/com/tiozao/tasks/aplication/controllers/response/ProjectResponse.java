package com.tiozao.tasks.aplication.controllers.response;

public class ProjectResponse {

    private String projectName;
    private String description;

    private String alias;

    public ProjectResponse(String projectName, String description, String alias) {
        this.projectName = projectName;
        this.description = description;
        this.alias = alias;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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
}
