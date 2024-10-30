package com.tiozao.tasks.domain.service.providers.gatewaypagamento.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CheckoutRepository extends JpaRepository<CheckoutEntity,Integer> {

    Optional<CheckoutEntity> findByExternalId(UUID externalId);

}
