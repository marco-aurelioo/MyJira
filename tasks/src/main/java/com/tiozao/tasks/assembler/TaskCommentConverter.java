package com.tiozao.tasks.assembler;

import com.tiozao.tasks.aplication.dtos.TaskCommentDto;
import com.tiozao.tasks.domain.entity.TaskCommentEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskCommentConverter extends Converter<TaskCommentDto, TaskCommentEntity> {

    public TaskCommentConverter() {
        super(TaskCommentConverter::originDomain, TaskCommentConverter::domainOrigin);
    }

    public static TaskCommentEntity originDomain(TaskCommentDto dto) {
        TaskCommentEntity entity = new TaskCommentEntity();
        entity.setId(dto.getId());
        entity.setComment(dto.getComment());
        return entity;
    }

    public static TaskCommentDto domainOrigin(TaskCommentEntity entity) {
        TaskCommentDto dto = new TaskCommentDto();
        dto.setId(entity.getId());
        dto.setComment(entity.getComment());
        return dto;
    }

}
