package com.tiozao.tasks.assembler.models;

import com.tiozao.tasks.aplication.controllers.request.PersonRequest;

import java.security.Principal;

public class PersonInputs implements ObjectsIn {
    private PersonRequest request;
    private Principal principal;

    public PersonInputs(PersonRequest request, Principal principal) {
        this.request = request;
        this.principal = principal;
    }

    public PersonRequest getRequest() {
        return request;
    }

    public Principal getPrincipal() {
        return principal;
    }
}
