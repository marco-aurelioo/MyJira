package com.tiozao.tasks.repository;

import com.tiozao.tasks.domain.entity.ProjectRole;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRoleRepository extends CrudRepository<ProjectRole,Long> {
    ProjectRole findByName(String name);
}
