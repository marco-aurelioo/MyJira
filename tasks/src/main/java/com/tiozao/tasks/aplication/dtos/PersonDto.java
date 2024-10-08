package com.tiozao.tasks.aplication.dtos;

import com.tiozao.tasks.aplication.controllers.request.PersonRequest;

import java.security.Principal;

public class PersonDto {

    private String name;
    private String avatar;

    public PersonDto(PersonRequest person, Principal principal) {

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


}
