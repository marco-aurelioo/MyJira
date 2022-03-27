package com.tiozao.tasks.resources.repositories;

import com.tiozao.tasks.domain.entity.TaskCommentEntity;
import com.tiozao.tasks.domain.entity.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskCommentRepository extends PagingAndSortingRepository<TaskCommentEntity,Integer> {
    Page<TaskCommentEntity> findByTask(TaskEntity task, Pageable page);
}
