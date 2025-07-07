package com.tiozao.tasks.services;

import com.tiozao.tasks.domain.entity.Project;
import com.tiozao.tasks.domain.entity.Task;
import com.tiozao.tasks.domain.entity.UserProfile;
import com.tiozao.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private UserProfileService profileService;

    @Autowired
    private ProjectService projectService;

    public Task createTask(Task task){
        //tem que validar permissão
        // ten que verificar campos de sequecia
        Project project = projectService.findMyProjectByUnicName(task.getProject().getUnicName());
        UserProfile createdBy = profileService.findMyUserProfile();
        task.setCreatedBy(createdBy);
        task.setProject(project);
        if(task.getTaskOwner()!=null){
            task.setTaskOwner(profileService.findUserByExternalId(task.getTaskOwner().getExternalId()));
        }
        task.setSequencia(getNewSequencia(project));

        return task;
    }

    private Integer getNewSequencia(Project project) {
        Integer sequencia = repository.findMaxSequenciaByProject(project);
        if(sequencia == null)
            return 1;
        return sequencia + 1;
    }

    public Page<Task> buscaTasks(String projectId, Pageable pageable){
        return Page.empty();
    }

    public Task atualizaTask(Task task){

        //carregar projeto

        //carregar usuarios
        //salvar task


        return task;
    }



}
