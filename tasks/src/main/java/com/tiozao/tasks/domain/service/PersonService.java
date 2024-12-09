package com.tiozao.tasks.domain.service;

import com.tiozao.tasks.domain.entity.PersonEntity;
import com.tiozao.tasks.domain.entity.ProjectEntity;
import com.tiozao.tasks.domain.service.providers.useraccess.UserRolesService;
import com.tiozao.tasks.resources.repositories.PersonReportReporsitoy;
import com.tiozao.tasks.resources.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PersonService {

    Logger logger = LoggerFactory.getLogger("PersonService");

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

    public Page<PersonEntity> findPersonLikeName(String nameLike, Pageable pageable) {
        //sera que queremos devolver todos os usuarios
        if(nameLike == null){
            return  reportReporsitoy.findAll(pageable);
        }
        return reportReporsitoy.findByNameLike('%'+nameLike+'%', pageable );
    }


    public Page<ProjectEntity> findMyProject(String myExternalId, Pageable pageable) {
        return repository.findProjectsByPersonId(myExternalId, pageable);
    }

    public boolean incluiProjeto(String myExternalId, ProjectEntity projeto) {
        PersonEntity pessoa = repository.findByUserId(myExternalId).orElseThrow();
        if(!pessoa.getProjectMember().contains(projeto)) {
            pessoa.getProjectMember().add(projeto);
            repository.save(pessoa);
            logger.info("Adicionando projeto;");
        }else{
            logger.info("Projeto ja adicionado;");
        }
        return true;
    }
}
