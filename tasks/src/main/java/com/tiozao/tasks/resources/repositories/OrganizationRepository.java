package com.tiozao.tasks.resources.repositories;

import com.tiozao.tasks.domain.entity.OrganizationEntity;
import com.tiozao.tasks.domain.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<OrganizationEntity,Integer> {

    List<OrganizationEntity> findByOwner(PersonEntity person);

    Optional<OrganizationEntity> findByNameAndOwner(String organizations, PersonEntity person);

    Optional<OrganizationEntity> findByExternalId(String organizationId);
}
