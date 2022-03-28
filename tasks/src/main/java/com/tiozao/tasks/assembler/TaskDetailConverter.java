package com.tiozao.tasks.assembler;

import com.tiozao.tasks.aplication.dtos.TaskDto;
import com.tiozao.tasks.domain.entity.TaskEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TaskDetailConverter extends Converter<TaskDto, TaskEntity> {

    public TaskDetailConverter() {
        super(TaskDetailConverter::originDomain, TaskDetailConverter::domainOrigin);
    }

    public static TaskEntity originDomain(TaskDto dto) {
        TaskEntity entity = new TaskEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public static TaskDto domainOrigin(TaskEntity entity) {
        TaskDto dto = new TaskDto();
        BeanUtils.copyProperties(entity, dto);
        dto.setStep(entity.getStep().getStepName());
        return dto;
    }

}
