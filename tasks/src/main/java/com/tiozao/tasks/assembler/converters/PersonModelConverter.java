package com.tiozao.tasks.assembler.converters;

import com.tiozao.tasks.aplication.dtos.PersonDto;
import com.tiozao.tasks.domain.entity.PersonEntity;
import org.springframework.stereotype.Component;

@Component
public class PersonModelConverter extends ConverterModel<PersonDto, PersonEntity>{
    public PersonModelConverter() {
        super(PersonModelConverter::domainOrigin, PersonModelConverter::originDomain);
    }

    private static PersonEntity domainOrigin(PersonDto dto) {
        throw new UnsupportedOperationException("Transformacao invalida");
    }

    private static PersonDto originDomain(PersonEntity entity) {
        return new PersonDto(
                entity.getName(),
                entity.getAvatar(),
                entity.getUserId());
    }
}
