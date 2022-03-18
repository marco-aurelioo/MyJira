package com.tiozao.tasks.domain.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="taks")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    private String title;
    private String description;
    private Integer complexity;
    private VALUE_SIZE value;
    private TASK_TYPE type;
    private String taskAlias;

    @ManyToOne
    private TaskEntity epicTask;

    @ManyToOne
    private StepEntity step;

    @ManyToOne
    private ProjectEntity project;

    @OneToMany
    private List<PersonEntity> executors;

    public String getTaskAlias() {
        return taskAlias;
    }

    public void setTaskAlias(String taskAlias) {
        this.taskAlias = taskAlias;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
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

    public TASK_TYPE getType() {
        return type;
    }

    public void setType(TASK_TYPE type) {
        this.type = type;
    }

    public TaskEntity getEpicTask() {
        return epicTask;
    }

    public void setEpicTask(TaskEntity epicTask) {
        this.epicTask = epicTask;
    }

    public VALUE_SIZE getValue() {
        return value;
    }

    public void setValue(VALUE_SIZE value) {
        this.value = value;
    }

    public StepEntity getStep() {
        return step;
    }

    public void setStep(StepEntity step) {
        this.step = step;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public List<PersonEntity> getExecutors() {
        return executors;
    }

    public void setExecutors(List<PersonEntity> executors) {
        this.executors = executors;
    }
}
