package com.tiozao.tasks.resources.repositories;

import com.tiozao.tasks.domain.entity.ProjectEntity;
import com.tiozao.tasks.domain.entity.StepEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StepRepository extends JpaRepository<StepEntity,Integer> {

    List<StepEntity> findByProjectOrderByOrderIdx(ProjectEntity project);

    StepEntity findByProjectAndStepName(ProjectEntity project, String stepName);

}
