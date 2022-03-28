package com.tiozao.tasks.assembler;

import com.tiozao.tasks.aplication.dtos.TaskCreateDto;
import com.tiozao.tasks.domain.entity.TaskEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TaskCreateConverter extends Converter<TaskCreateDto, TaskEntity> {

    public TaskCreateConverter() {
        super(TaskCreateConverter::originDomain, TaskCreateConverter::domainOrigin);
    }

    public static TaskEntity originDomain(TaskCreateDto dto) {
        TaskEntity entity = new TaskEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public static TaskCreateDto domainOrigin(TaskEntity entity) {
        TaskCreateDto dto = new TaskCreateDto();
        dto.setTitle(entity.getTitle());
        BeanUtils.copyProperties(entity, dto);
        dto.setStep(entity.getStep().getStepName());
        return dto;
    }

}
