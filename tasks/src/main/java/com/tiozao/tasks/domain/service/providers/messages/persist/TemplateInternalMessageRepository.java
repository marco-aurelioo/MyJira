package com.tiozao.tasks.domain.service.providers.messages.persist;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TemplateInternalMessageRepository extends CrudRepository<TemplateInternalMessageEntity,Integer> {

    Optional<TemplateInternalMessageEntity> findByTemplateAndOwner(String template, String owner);

}
