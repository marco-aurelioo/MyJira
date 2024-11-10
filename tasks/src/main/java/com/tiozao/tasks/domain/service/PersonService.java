package com.tiozao.tasks.domain.service;

import com.tiozao.tasks.domain.entity.PersonEntity;
import com.tiozao.tasks.domain.service.providers.useraccess.UserRolesService;
import com.tiozao.tasks.resources.repositories.PersonReportReporsitoy;
import com.tiozao.tasks.resources.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PersonReportReporsitoy reportReporsitoy;

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

    public Page<PersonEntity> findPersonLikeName(Integer nameLike, Pageable pageable) {
        //sera que queremos devolver todos os usuarios
        if(nameLike == null){
            return  reportReporsitoy.findAll(pageable);
        }
        return reportReporsitoy.findByNameLike(nameLike, pageable );
    }
}
