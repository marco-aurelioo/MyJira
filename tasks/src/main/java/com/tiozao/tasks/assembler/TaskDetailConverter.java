package com.tiozao.tasks.assembler;

import com.tiozao.tasks.aplication.dtos.TaskDto;
import com.tiozao.tasks.domain.entity.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskDetailConverter extends Converter<TaskDto, TaskEntity> {

    public TaskDetailConverter() {
        super(TaskDetailConverter::originDomain, TaskDetailConverter::domainOrigin);
    }

    public static TaskEntity originDomain(TaskDto dto){
        TaskEntity entity = new TaskEntity();
        entity.setTaskAlias(dto.getAlias());
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setComplexity(dto.getComplexity());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    public static TaskDto domainOrigin(TaskEntity entity){
        TaskDto dto = new TaskDto();
        dto.setTaskAlias(entity.getTaskAlias());
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setComplexity(entity.getComplexity());
        dto.setDescription(entity.getDescription());
        dto.setStep(entity.getStep().getStepName());
        return dto;
    }

}
