package com.tiozao.tasks.assembler;

import com.tiozao.tasks.aplication.dtos.TaskCommentDto;
import com.tiozao.tasks.domain.entity.TaskCommentEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TaskCommentConverter extends Converter<TaskCommentDto, TaskCommentEntity> {

    public TaskCommentConverter() {
        super(TaskCommentConverter::originDomain, TaskCommentConverter::domainOrigin);
    }

    public static TaskCommentEntity originDomain(TaskCommentDto dto) {
        TaskCommentEntity entity = new TaskCommentEntity();
        BeanUtils.copyProperties(dto, entity);
        entity.setPerson(PersonConverter.originDomainPerson(dto.getPerson()));
        return entity;
    }

    public static TaskCommentDto domainOrigin(TaskCommentEntity entity) {
        TaskCommentDto dto = new TaskCommentDto();
        BeanUtils.copyProperties(entity, dto);
        dto.setPerson(PersonConverter.domainOriginDto(entity.getPerson()));
        return dto;
    }

}
