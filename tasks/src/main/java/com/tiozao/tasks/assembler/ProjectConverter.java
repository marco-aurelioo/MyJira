package com.tiozao.tasks.assembler;

import com.tiozao.tasks.aplication.dtos.ProjectDto;
import com.tiozao.tasks.domain.entity.ProjectEntity;
import org.springframework.stereotype.Component;

@Component
public class ProjectConverter extends Converter<ProjectDto, ProjectEntity>{

    public ProjectConverter() {
        super(ProjectConverter::originDomainEntity, ProjectConverter::domainOriginDto);
    }

    public static ProjectEntity originDomainEntity(ProjectDto dto){
        ProjectEntity entity = new ProjectEntity();
        entity.setProjectAlias(dto.getAlias());
        entity.setProjectName(dto.getProjectName());
        entity.setDescription(dto.getDescription());
        entity.setOwner(PersonConverter.originDomainPerson(dto.getOwner()));
        return entity;
    }

    public static ProjectDto domainOriginDto(ProjectEntity entity){
        ProjectDto dto = new ProjectDto();
        dto.setAlias(entity.getProjectAlias());
        dto.setProjectName(entity.getProjectName());
        dto.setDescription(entity.getDescription());
        dto.setOwner(PersonConverter.domainOriginDto(entity.getOwner()));
        return dto;
    }
}
