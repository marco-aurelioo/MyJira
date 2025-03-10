package com.tiozao.tasks.services;

import com.tiozao.tasks.domain.entity.Project;
import com.tiozao.tasks.repository.ProjectRepository;
import com.tiozao.tasks.utils.NomeHashGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PrivateProjectService {

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private ProjectRepository repository;


    public Project  createProject(Project project){

        Project entity = new Project();
        entity.setOwner(userProfileService.findMyUserProfile());
        entity.setName(project.getName());
        entity.setDescription(project.getDescription());
        entity.setExternalId(UUID.randomUUID().toString());
        entity.setPublic(project.isPublic());
        entity.setUnicName(createUnicName(project.getName()));

        return repository.save(entity);

    }

    public Project findProjectByExternalId(String externalId) {
        return repository.findByExternalIdAndOwner(
                externalId,
                userProfileService.findMyUserProfile())
                .orElseThrow();
    }

    public Project findProjectByUnicName(String unicName) {
        return repository.findByUnicNameAndOwner(
                unicName,
                userProfileService.findMyUserProfile())
                .orElseThrow();
    }


    private String createUnicName(String originalName){
        String unicName = NomeHashGenerator.gerarHash(originalName);
        long count = repository.countByRegExpUnicName("^"+unicName+"[0-9]*$");
        if(count == 0){
            return  unicName;
        }else{
            return unicName + count;
        }
    }


    public Page<Project> findProjectByOwner(PageRequest pageable) {
        return repository.findByOwner(userProfileService.findMyUserProfile(), pageable);
    }
}
