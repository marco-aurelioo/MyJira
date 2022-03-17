package com.tiozao.tasks.aplication.dtos;

public class ProjectDto {

    private String alias;
    private String projectName;
    private String description;
    private PersonDto owner;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
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

    public PersonDto getOwner() {
        return owner;
    }

    public void setOwner(PersonDto owner) {
        this.owner = owner;
    }
}
