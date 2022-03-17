package com.tiozao.tasks.aplication.dtos;

public class TaskDto {

    private String alias;
    private String title;
    private String description;
    private String priorioty;
    private String step;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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

    public String getPriorioty() {
        return priorioty;
    }

    public void setPriorioty(String priorioty) {
        this.priorioty = priorioty;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }
}
