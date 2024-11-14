package com.tiozao.tasks.domain.service;

import com.tiozao.tasks.domain.entity.OrganizationEntity;
import com.tiozao.tasks.domain.entity.PersonEntity;
import com.tiozao.tasks.resources.repositories.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository repository;

    @Autowired
    private PersonService personService;
    public List<OrganizationEntity> getOrganizations(String pessoaId){
        PersonEntity person = personService.findPersonByUserId(pessoaId);
        return repository.findByOwner(person);
    }

    public void criarOrganizations(String organizationName, String pessoaId){
        PersonEntity person = personService.findPersonByUserId(pessoaId);

        OrganizationEntity entity = new OrganizationEntity();
        entity.setName(organizationName);
        entity.setOwner(person);
        entity.setAdms(new ArrayList<>());
        entity.getAdms().add(person);

        repository.save(entity);

    }

    public OrganizationEntity findORganizationByName(String pessoaId, String organizations) {
        PersonEntity person = personService.findPersonByUserId(pessoaId);
        return repository.findByNameAndOwner(organizations, person).orElseThrow();
    }

}
