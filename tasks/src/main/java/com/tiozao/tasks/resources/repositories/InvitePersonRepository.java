package com.tiozao.tasks.resources.repositories;

import com.tiozao.tasks.domain.entity.InvitePersonEntity;
import com.tiozao.tasks.domain.entity.ProjectEntity;
import com.tiozao.tasks.domain.entity.STATUS_INVITE;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InvitePersonRepository extends CrudRepository<InvitePersonEntity, Integer> {

    List<InvitePersonEntity> findByProjectAndExternalPersonIdIn(ProjectEntity projectEntity, List<String> listaDeUsuarios);

    Page<InvitePersonEntity> findByExternalPersonId(String myExternalId, Pageable pageable);

    Page<InvitePersonEntity> findByExternalPersonIdAndStatusIn(String myExternalId, List<STATUS_INVITE> enviado, Pageable pageable);


}
