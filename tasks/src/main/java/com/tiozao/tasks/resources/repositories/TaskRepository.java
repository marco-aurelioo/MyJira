package com.tiozao.tasks.resources.repositories;

import com.tiozao.tasks.domain.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity,Integer> {
}
