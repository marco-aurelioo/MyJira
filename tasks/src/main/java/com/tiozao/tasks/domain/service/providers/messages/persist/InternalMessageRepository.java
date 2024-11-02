package com.tiozao.tasks.domain.service.providers.messages.persist;

import org.springframework.data.repository.CrudRepository;

public interface InternalMessageRepository extends CrudRepository<InternalMessageEntity, Integer> {

}
