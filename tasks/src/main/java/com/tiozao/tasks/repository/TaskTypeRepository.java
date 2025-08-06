package com.tiozao.tasks.repository;

import com.tiozao.tasks.domain.entity.ProjectTaskType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskTypeRepository extends CrudRepository<ProjectTaskType, Integer> {
}
