package com.tiozao.tasks.aplication.converter;

import com.tiozao.tasks.aplication.controllers.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TaksConverter {

    @Autowired
    private UserProfileConverter profileConverter;

    @Autowired
    private ProjectConverter projectConverter;

    public Task convertToDto(com.tiozao.tasks.domain.entity.Task task) {
        Task dto = new Task();
        dto.setId(task.getProject().getUnicName()+"-"+task.getId());
        dto.setDescricao(task.getDescricao());
        dto.setTitulo(task.getTitulo());
        dto.setCriador(profileConverter.convertToDto(task.getCreatedBy()));
        dto.setResponsavel(profileConverter.convertToDto(task.getTaskOwner()));
        dto.setProjeto(projectConverter.convertToDto(task.getProject()));

        return dto;
    }


    public com.tiozao.tasks.domain.entity.Task convertToEntity(Task task) {
        com.tiozao.tasks.domain.entity.Task entity = new com.tiozao.tasks.domain.entity.Task();
        entity.setDescricao(task.getDescricao());
        entity.setTitulo(task.getTitulo());
        entity.setProject(projectConverter.convertToEntity(task.getProjeto()));
        entity.setCreatedBy(profileConverter.convertToEntity(task.getCriador()));
        entity.setTaskOwner(profileConverter.convertToEntity(task.getResponsavel()));

        return entity;
    }
}
