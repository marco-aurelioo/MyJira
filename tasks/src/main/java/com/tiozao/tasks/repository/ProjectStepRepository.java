package com.tiozao.tasks.repository;

import com.tiozao.tasks.domain.entity.Project;
import com.tiozao.tasks.domain.entity.ProjectStep;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectStepRepository extends CrudRepository<ProjectStep, Integer> {

    List<ProjectStep> findByProject(Project project);
}
