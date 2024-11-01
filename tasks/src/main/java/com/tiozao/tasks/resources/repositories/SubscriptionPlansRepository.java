package com.tiozao.tasks.resources.repositories;

import com.tiozao.tasks.domain.entity.SubscriptionPlansEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SubscriptionPlansRepository extends CrudRepository<SubscriptionPlansEntity,Integer > {

    Optional<SubscriptionPlansEntity> findByNome(String nome);

}
