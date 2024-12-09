package com.tiozao.tasks.resources.repositories;

import com.tiozao.tasks.domain.entity.OrganizationEntity;
import com.tiozao.tasks.domain.entity.ProjectEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends CrudRepository<ProjectEntity,Integer> {
    ProjectEntity findByProjectAlias(String alias);

    List<ProjectEntity> findByOrganization(OrganizationEntity organization);

    Optional<ProjectEntity> findByExternalId(String projectId);
}
