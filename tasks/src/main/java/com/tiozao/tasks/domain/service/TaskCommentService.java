package com.tiozao.tasks.domain.service;

import com.tiozao.tasks.domain.entity.PersonEntity;
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

    @Autowired
    private PersonService personService;

    public TaskCommentEntity createComment(String taskAlias, TaskCommentEntity task) {
        PersonEntity person = personService.findPerson(task.getPerson().getId());
        task.setTask(taskService.findByTaskAlias(taskAlias));
        task.setPerson(person);
        return taskCommentRepository.save(task);
    }

    public Page<TaskCommentEntity> findComments(String taskAlias, PageRequest pageable) {
        return taskCommentRepository.findByTask(taskService.findByTaskAlias(taskAlias), pageable);
    }
}
