package com.tiozao.tasks.domain.service;

import com.tiozao.tasks.domain.entity.PersonEntity;
import com.tiozao.tasks.resources.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public PersonEntity createPerson(PersonEntity person){
        return repository.save(person);
    }

    public PersonEntity findPerson(Integer id) {
        if(id == null)
            return null;
        return repository.findById(id).get();
    }

}
