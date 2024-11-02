package com.tiozao.tasks.assembler.converters.models;

import com.tiozao.tasks.aplication.dtos.PersonDto;
import com.tiozao.tasks.domain.entity.PersonEntity;

public class PersonModelIn implements ObjectsIn {


    private String name;
    private String avatar;
    private String pessoaId;

    public PersonModelIn(String name, String avatar, String pessoaId) {
        this.name = name;
        this.avatar = avatar;
        this.pessoaId = pessoaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(String pessoaId) {
        this.pessoaId = pessoaId;
    }
}
