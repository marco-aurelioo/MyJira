package com.tiozao.tasks.resources.repositories;

import com.tiozao.tasks.domain.entity.ProjectEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<ProjectEntity,Integer> {
    ProjectEntity findByProjectAlias(String alias);
}
