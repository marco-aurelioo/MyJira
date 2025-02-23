package com.tiozao.tasks.aplication.controllers.model;

public class Project {

    private String projectId;
    private String name;
    private String description;
    private boolean isPublic;

    private String unicName;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public String getUnicName() {
        return unicName;
    }

    public void setUnicName(String unicName) {
        this.unicName = unicName;
    }
}

