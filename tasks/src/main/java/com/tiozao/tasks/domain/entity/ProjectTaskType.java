package com.tiozao.tasks.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name="project_task_type")
public class ProjectTaskType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_task_type_seq_gen")
    @SequenceGenerator(name = "project_task_type_seq_gen", sequenceName = "project_task_type_seq", allocationSize = 1)
    private Integer id;
    private String TypeName;
    private String colorCode;

    @OneToOne
    private Project project;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
