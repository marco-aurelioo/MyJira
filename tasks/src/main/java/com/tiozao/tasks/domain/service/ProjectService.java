package com.tiozao.tasks.domain.service;

import com.tiozao.tasks.domain.entity.PersonEntity;
import com.tiozao.tasks.domain.entity.ProjectEntity;
import com.tiozao.tasks.resources.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository repository;


    @Autowired
    private PersonService personService;

    public ProjectEntity create(ProjectEntity project) {
        validateOwner(project);
        ProjectEntity new_project = repository.save(project);
        return new_project;
    }

    private void validateOwner(ProjectEntity project) {
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

}
