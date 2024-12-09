package com.tiozao.tasks.domain.service;

import com.tiozao.tasks.aplication.controllers.response.ProjectResponse;
import com.tiozao.tasks.domain.entity.OrganizationEntity;
import com.tiozao.tasks.domain.entity.PersonEntity;
import com.tiozao.tasks.domain.entity.ProjectEntity;
import com.tiozao.tasks.resources.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository repository;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private PersonService personService;

    public ProjectEntity create(ProjectEntity project) {
        validateOwner(project);
        project.setExternalId(UUID.randomUUID().toString());
        ProjectEntity new_project = repository.save(project);
        return new_project;
    }

    private void validateOwner(ProjectEntity project) {
        //validar regras de negocio quantidade de projetos criados por owner

        PersonEntity owner = personService.findPerson(project.getOwner().getId());
        if (owner == null) {
            owner = personService.createPerson(project.getOwner());
        }
        owner.getProjectMember().add(project);
        project.setOwner(owner);
    }

    public ProjectEntity find(String alias) {
        return repository.findByProjectAlias(alias);
    }

    public boolean delete(String alias) {
        ProjectEntity project = repository.findByProjectAlias(alias);
        repository.delete(project);
        return true;
    }

    public List<ProjectEntity> findByOrganizations(String userId, String organizations) {
        PersonEntity owner = personService.findPersonByUserId(userId);
        OrganizationEntity organization = organizationService.findORganizationById(organizations);
        return repository.findByOrganization(organization);
    }

    public ProjectEntity findByExternalId(String projectId) {
        return repository.findByExternalId(projectId).orElseThrow();
    }
}
