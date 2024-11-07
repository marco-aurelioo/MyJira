package com.tiozao.tasks.resources.repositories;

import com.tiozao.tasks.domain.entity.OrganizationEntity;
import com.tiozao.tasks.domain.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<OrganizationEntity,Integer> {

    Optional<OrganizationEntity> findByName(String organizations);
    List<OrganizationEntity> findByOwner(PersonEntity person);

}
