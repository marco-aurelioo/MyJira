package com.tiozao.tasks.assembler;

import com.tiozao.tasks.aplication.dtos.PersonDto;
import com.tiozao.tasks.domain.entity.PersonEntity;
import org.springframework.stereotype.Component;

@Component
public class PersonConverter extends Converter<PersonDto, PersonEntity> {

    public PersonConverter() {
        super(PersonConverter::originDomainPerson, PersonConverter::domainOriginDto);
    }

    public static PersonEntity originDomainPerson(PersonDto dto){
        PersonEntity entity = new PersonEntity();
        entity.setId(dto.getId());
        entity.setAvatar(dto.getAvatar());
        entity.setName(dto.getName());
        return entity;
    }

    public static PersonDto domainOriginDto(PersonEntity entity){
        PersonDto dto = new PersonDto();
        dto.setId(entity.getId());
        dto.setAvatar(entity.getAvatar());
        dto.setName(entity.getName());
        return dto;
    }
}
