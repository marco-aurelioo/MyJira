package com.tiozao.tasks.domain.service;

import com.tiozao.tasks.domain.entity.TaskCommentEntity;
import com.tiozao.tasks.resources.repositories.TaskCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class TaskCommentService {

    @Autowired
    private TaskCommentRepository taskCommentRepository;

    @Autowired
    private TaskService taskService;

    public TaskCommentEntity createComment(String taskAlias, TaskCommentEntity task) {
        task.setTask(taskService.findByTaskAlias(taskAlias));
        return taskCommentRepository.save(task);
    }

    public Page<TaskCommentEntity> findComments(String taskAlias, PageRequest pageable) {
        return taskCommentRepository.findByTask(taskService.findByTaskAlias(taskAlias), pageable);
    }
}
