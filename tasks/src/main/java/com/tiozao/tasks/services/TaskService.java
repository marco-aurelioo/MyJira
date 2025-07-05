package com.tiozao.tasks.services;

import com.tiozao.tasks.domain.entity.Task;
import com.tiozao.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public Task createTask(Task task){
        return task;
    }

    public Page<Task> buscaTasks(String projectId, Pageable pageable){
        return Page.empty();
    }

    public Task atualizaTask(Task task){
        return task;
    }

}
