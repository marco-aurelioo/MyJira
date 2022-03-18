package com.tiozao.tasks.domain.service;

import com.tiozao.tasks.domain.entity.ProjectEntity;
import com.tiozao.tasks.domain.entity.StepEntity;
import com.tiozao.tasks.resources.repositories.StepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StepServices {

    @Autowired
    private StepRepository repository;

    public StepEntity createStep(ProjectEntity project, String stepName, int order,String stepNext, String previousStep) {
        StepEntity step_entity = createStepEntity(project,stepName,order);
        step_entity.setPreviousStep(repository.findByProjectAndStepName(project,previousStep));
        step_entity.setNextStep(repository.findByProjectAndStepName(project,stepNext));
        return repository.save(step_entity);
    }

    private StepEntity createStepEntity(ProjectEntity project,String stepName,Integer order){
        StepEntity step_entity = new StepEntity();
        step_entity.setProject(project);
        step_entity.setStepName(stepName);
        step_entity.setOrderIdx(order);
        return step_entity;
    }

    public void createDefaultSteps(ProjectEntity project) {
        cleanAllSteps(project);
        StepEntity todo  = repository.save(createStepEntity(project,"TODO",0));
        StepEntity doing = repository.save(createStepEntity(project,"Doing",1));
        StepEntity test  = repository.save(createStepEntity(project,"Test",2));
        StepEntity done  = repository.save(createStepEntity(project, "Done",3));

        todo.setNextStep(doing);
        doing.setNextStep(test);
        doing.setPreviousStep(todo);
        test.setPreviousStep(doing);
        test.setNextStep(done);
        done.setPreviousStep(test);

        repository.save(todo);
        repository.save(doing);
        repository.save(test);
        repository.save(done);
    }

    private void cleanAllSteps(ProjectEntity project) {
        List<StepEntity> steps = repository.findByProjectOrderByOrderIdx(project);
        repository.deleteAll(steps);
    }

    public StepEntity getFristStepForProject(ProjectEntity project) {
        return repository.findByProjectOrderByOrderIdx(project).get(0);
    }
}
