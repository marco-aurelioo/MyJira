package com.tiozao.tasks.resources.repositories;

import com.tiozao.tasks.domain.entity.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<OrganizationEntity,Integer> {

    Optional<OrganizationEntity> findByName(String organizations);
}
