package com.tiozao.tasks.assembler.models;

import com.tiozao.tasks.aplication.controllers.request.ProjectRequest;

import java.security.Principal;

public class ProjectIn implements ObjectsIn {

    private String organizations;

    private ProjectRequest projectDto;
     private Principal principal;

    public ProjectIn(){}

    public ProjectIn(String organizations, ProjectRequest projectDto, Principal principal) {
        this.organizations = organizations;
        this.projectDto = projectDto;
        this.principal = principal;
    }

    public String getOrganizations() {
        return organizations;
    }

    public void setOrganizations(String organizations) {
        this.organizations = organizations;
    }

    public ProjectRequest getProjectDto() {
        return projectDto;
    }

    public void setProjectDto(ProjectRequest projectDto) {
        this.projectDto = projectDto;
    }

    public Principal getPrincipal() {
        return principal;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }
}
