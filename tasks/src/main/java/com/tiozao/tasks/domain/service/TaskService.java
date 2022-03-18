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

    @Autowired
    private StepServices stepServices;

    public Page<TaskEntity> findAllTasks(String alias, Pageable pageReq) {
        ProjectEntity project = projectService.find(alias);
        return repository.findByProject(project,pageReq);
    }

    public TaskEntity createTask(String alias, TaskEntity taskEntity) {
        ProjectEntity project = projectService.find(alias);
        taskEntity.setProject(project);
        taskEntity.setStep(stepServices.getFristStepForProject(project));
        taskEntity = repository.save(taskEntity);
        return includeTaskAlias(taskEntity);
    }

    private TaskEntity includeTaskAlias(TaskEntity taskEntity) {
        Integer qtd = repository.countByProject(taskEntity.getProject());
        taskEntity.setTaskAlias(taskEntity.getProject().getProjectAlias()+"-"+qtd);
        return repository.save(taskEntity);
    }
}
