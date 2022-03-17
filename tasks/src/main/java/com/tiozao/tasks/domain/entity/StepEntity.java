package com.tiozao.tasks.domain.entity;

import javax.persistence.*;

@Entity
@Table(name="project_steps")
public class StepEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String stepName;

    @ManyToOne
    private StepEntity nextStep;

    @ManyToOne
    private StepEntity previousStep;
    private Integer orderIdx;

    @ManyToOne
    private ProjectEntity project;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public StepEntity getNextStep() {
        return nextStep;
    }

    public void setNextStep(StepEntity nextStep) {
        this.nextStep = nextStep;
    }

    public StepEntity getPreviousStep() {
        return previousStep;
    }

    public void setPreviousStep(StepEntity previousStep) {
        this.previousStep = previousStep;
    }

    public Integer getOrderIdx() {
        return orderIdx;
    }

    public void setOrderIdx(Integer orderIdx) {
        this.orderIdx = orderIdx;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }
}
