package com.tiozao.tasks.assembler.models;

import com.tiozao.tasks.domain.entity.PersonEntity;

public class PersonOutputs implements ObjectsOut {

    private PersonEntity entity;

    public PersonOutputs(PersonEntity entity) {
        this.entity = entity;
    }

    public PersonEntity getEntity() {
        return entity;
    }
}
