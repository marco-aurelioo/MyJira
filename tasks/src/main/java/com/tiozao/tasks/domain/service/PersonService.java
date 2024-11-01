package com.tiozao.tasks.domain.service;

import com.tiozao.tasks.domain.entity.PersonEntity;
import com.tiozao.tasks.domain.service.providers.useraccess.UserRolesService;
import com.tiozao.tasks.resources.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private UserRolesService userRolesService;

    public PersonEntity createPerson(PersonEntity person) {
        PersonEntity personEntity = repository.save(person);
        userRolesService.addRole(personEntity.getUserId(),"USER_ROLE");
        return personEntity;
    }

    public PersonEntity findPerson(Integer id) {
        if (id == null)
            return null;
        return repository.findById(id).get();
    }

    public PersonEntity findPersonByUserId(String userId) {
        return repository.findByUserId( userId).orElseThrow(IllegalStateException::new);
    }

}
