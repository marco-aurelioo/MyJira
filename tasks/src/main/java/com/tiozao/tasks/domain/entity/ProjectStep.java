package com.tiozao.tasks.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "project_steps")
public class ProjectStep {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_step_seq_gen")
    @SequenceGenerator(name = "project_step_seq_gen", sequenceName = "project_step_seq", allocationSize = 1)
    private Integer id;
    private String StatusName;
    private String colorCode;
    private Integer stepOrder;
    private Integer maxWhip;

    private Boolean editable;

    @ManyToOne
    private Project project;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatusName() {
        return StatusName;
    }

    public void setStatusName(String statusName) {
        StatusName = statusName;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public Integer getStepOrder() {
        return stepOrder;
    }

    public void setStepOrder(Integer stepOrder) {
        this.stepOrder = stepOrder;
    }

    public Integer getMaxWhip() {
        return maxWhip;
    }

    public void setMaxWhip(Integer maxWhip) {
        this.maxWhip = maxWhip;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }
}
