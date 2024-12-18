package com.tiozao.tasks.aplication.dtos;

import com.tiozao.tasks.aplication.controllers.request.PersonRequest;

import java.security.Principal;

public class PersonDto {

    private String name;
    private String avatar;

    private String pessoaId;

    public PersonDto(String name, String avatar, String pessoaId) {
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
