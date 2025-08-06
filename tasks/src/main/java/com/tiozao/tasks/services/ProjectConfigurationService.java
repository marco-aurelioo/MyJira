package com.tiozao.tasks.services;

import com.tiozao.tasks.domain.entity.Project;
import com.tiozao.tasks.domain.entity.ProjectStep;
import com.tiozao.tasks.domain.entity.ProjectTaskType;
import com.tiozao.tasks.repository.ProjectRepository;
import com.tiozao.tasks.repository.ProjectStepRepository;
import com.tiozao.tasks.repository.TaskTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ProjectConfigurationService {

    @Autowired
    private ProjectStepRepository repository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskTypeRepository taskTypeRepository;

    public void saveProjectSteps(List<ProjectStep> steps){
        steps.sort(Comparator.comparing(ProjectStep::getStepOrder));
        if(!steps.isEmpty()) {
            Project project = projectRepository.findByUnicName(steps.get(0).getProject().getUnicName()).orElseThrow();
            List<ProjectStep> actualSteps = repository.findByProject(project);
            validarSteps(steps);
            repository.deleteAll(actualSteps);
            repository.saveAll(steps);
        }else{
            throw new IllegalArgumentException("Lista de steps invalida!");
        }
    }

    private void validarSteps(List<ProjectStep> steps) {
        if(!steps.get(0).getStepOrder().equals(0) && !steps.get(0).getStatusName().equals("Back Log") &&
           !steps.get(steps.size() -1).getStepOrder().equals(999) && !steps.get(steps.size() -1).getStatusName().equals("Done")){
            throw new IllegalArgumentException("falha na etrutura basica de steps.");
        }
    }


    public void createDefaultSteps(Project project){
        List<ProjectStep> listaSteps = new ArrayList<>();
        listaSteps.add(createProjectStep(project,0,"Back Log", "#D3D3D3", 0,false));
        listaSteps.add( createProjectStep(project,0,"To do", "#ADD8E6", 1,true));
        listaSteps.add( createProjectStep(project,0,"In Progress", "#FFD700", 2,true));
        listaSteps.add(createProjectStep(project,0,"Test", "#FFA500", 3,true));
        listaSteps.add(createProjectStep(project,0,"Review", "#BA55D3", 4,true));
        listaSteps.add(createProjectStep(project,0,"Done", "#32CD32", 999,false));
        repository.saveAll(listaSteps);
    }

    public void createDefaultTaksTypes(Project project){

        List<ProjectTaskType> listaTaskType = new ArrayList<>();
        listaTaskType.add(createTaskType(project,"Task","#1E90FF",false));
        listaTaskType.add(createTaskType(project,"Story","#90EE90",true));
        listaTaskType.add(createTaskType(project,"Bug","#FF6347",true));
        listaTaskType.add(createTaskType(project,"Problem","#8B0000",true));
        listaTaskType.add(createTaskType(project,"Epic","#9370DB",true));
        listaTaskType.add(createTaskType(project,"Impediment","#FF8C00",true));
        taskTypeRepository.saveAll(listaTaskType);

    }

    private ProjectTaskType createTaskType(Project project, String name, String color, boolean b) {
        ProjectTaskType taskType = new ProjectTaskType();
        taskType.setTypeName(name);
        taskType.setColorCode(color);
        taskType.setProject(project);
        return taskType;
    }

    private ProjectStep createProjectStep(Project project,int order, String name, String colorCode, int wip, Boolean editable) {
        ProjectStep projectStep = new ProjectStep();
        projectStep.setProject(project);
        projectStep.setStepOrder(order);
        projectStep.setStatusName(name);
        projectStep.setColorCode(colorCode);
        projectStep.setMaxWhip(wip);
        projectStep.setEditable(editable);
        return projectStep;
    }

}
