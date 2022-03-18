package com.tiozao.tasks.aplication.dtos;

public class TaskCreateDto {

    private String title;
    private String description;
    private Integer complexity;
    private String taskAlias;
    private Integer createByPersonId;

    public String getTaskAlias() {
        return taskAlias;
    }

    public void setTaskAlias(String taskAlias) {
        this.taskAlias = taskAlias;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getComplexity() {
        return complexity;
    }

    public void setComplexity(Integer complexity) {
        this.complexity = complexity;
    }

    public Integer getCreateByPersonId() {
        return createByPersonId;
    }

    public void setCreateByPersonId(Integer createByPersonId) {
        this.createByPersonId = createByPersonId;
    }
}
