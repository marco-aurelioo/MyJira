package com.tiozao.tasks.domain.service;

import com.tiozao.tasks.domain.entity.ProjectEntity;
import com.tiozao.tasks.domain.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private ProjectService projectService;

    public Page<TaskEntity> findAllTasks(String alias) {

        ProjectEntity project = projectService.findByAlias(alias);


        return null;
    }
}
