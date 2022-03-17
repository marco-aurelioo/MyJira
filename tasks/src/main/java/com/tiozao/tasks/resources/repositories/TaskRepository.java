package com.tiozao.tasks.resources.repositories;

import com.tiozao.tasks.domain.entity.ProjectEntity;
import com.tiozao.tasks.domain.entity.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity,Integer> {

    Page<TaskEntity> findByProject(ProjectEntity project, Pageable pageReq);

}
