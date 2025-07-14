package com.tiozao.tasks.aplication.converter;

import com.tiozao.tasks.aplication.controllers.model.TaskDto;
import com.tiozao.tasks.domain.entity.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


@Component
public class TaksConverter {

    @Autowired
    private UserProfileConverter profileConverter;

    @Autowired
    private ProjectConverter projectConverter;

    public TaskDto convertToDto(com.tiozao.tasks.domain.entity.Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getProject().getUnicName()+"-"+task.getSequencia());
        BeanUtils.copyProperties(task,dto);

        if(task.getCreatedBy()!= null){
            dto.setCriador(profileConverter.convertToDto(task.getCreatedBy()));
        }
        if(task.getTaskOwner()!= null) {
            dto.setResponsavel(profileConverter.convertToDto(task.getTaskOwner()));
        }
        dto.setProjeto(projectConverter.convertToDto(task.getProject()));

        return dto;
    }


    public com.tiozao.tasks.domain.entity.Task convertToEntity(String taskId, TaskDto task) {
        Task task_entity = convertToEntity(task);
        String sequencia = taskId.replace(
                task.getProjeto().getUnicName(), "")
                .replace("-","");
        task_entity.setSequencia(Integer.parseInt(sequencia));
        return task_entity;
    }

    public com.tiozao.tasks.domain.entity.Task convertToEntity(TaskDto task) {
        com.tiozao.tasks.domain.entity.Task entity = new com.tiozao.tasks.domain.entity.Task();
        BeanUtils.copyProperties(task,entity);
        entity.setProject(projectConverter.convertToEntity(task.getProjeto()));
        if(task.getCriador()!= null) {
            entity.setCreatedBy(profileConverter.convertToEntity(task.getCriador()));
        }
        if(task.getResponsavel()!= null) {
            entity.setTaskOwner(profileConverter.convertToEntity(task.getResponsavel()));
        }

        return entity;
    }

    public Page<TaskDto> convertToPageDto(Page<com.tiozao.tasks.domain.entity.Task> tasks) {
        return tasks.map(this::convertToDto);
    }
}
