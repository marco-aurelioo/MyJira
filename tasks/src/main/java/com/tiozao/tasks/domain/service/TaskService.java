package com.tiozao.tasks.domain.service;

import com.tiozao.tasks.domain.entity.ProjectEntity;
import com.tiozao.tasks.domain.entity.TaskEntity;
import com.tiozao.tasks.resources.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TaskRepository repository;

    public Page<TaskEntity> findAllTasks(String alias, Pageable pageReq) {
        ProjectEntity project = projectService.find(alias);
        return repository.findByProject(project,pageReq);
    }
}
