package com.tiozao.tasks.aplication.converter;

import com.tiozao.tasks.aplication.controllers.model.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectConverter {


    public Project convertToDto(com.tiozao.tasks.domain.entity.Project project) {
        Project dto = new Project();
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        dto.setProjectId(project.getExternalId());
        dto.setPublic(project.isPublic());
        dto.setUnicName(project.getUnicName());
        return dto;
    }


    public com.tiozao.tasks.domain.entity.Project convertToEntity(Project project) {
        com.tiozao.tasks.domain.entity.Project entity = new com.tiozao.tasks.domain.entity.Project();
        entity.setName(project.getName());
        entity.setUnicName(project.getUnicName());
        entity.setDescription(project.getDescription());
        entity.setPublic(project.isPublic());
        entity.setExternalId(project.getProjectId());
        return entity;
    }
}
