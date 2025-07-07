package com.tiozao.tasks.services;

import com.tiozao.tasks.domain.entity.Project;
import com.tiozao.tasks.domain.entity.ProjectMember;
import com.tiozao.tasks.repository.ProjectMemberRepository;
import com.tiozao.tasks.repository.ProjectRepository;
import com.tiozao.tasks.utils.NomeHashGenerator;
import org.keycloak.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProjectService {

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private ProjectRepository repository;

    @Autowired
    private ProjectMemberRepository projectMemberRepository;


    public Project  createProject(Project project){
        if(StringUtil.isNullOrEmpty(project.getName())){
            throw new IllegalArgumentException("Projeto precisa ter um nome");
        }
        Project entity = new Project();
        entity.setOwner(userProfileService.findMyUserProfile());
        entity.setName(project.getName());
        entity.setDescription(project.getDescription());
        entity.setExternalId(UUID.randomUUID().toString());
        entity.setPublic(project.isPublic());
        entity.setUnicName(createUnicName(project.getName()));

        return repository.save(entity);

    }

    public Project findMyProjectByExternalId(String externalId) {
        return repository.findByExternalIdAndOwner(
                externalId,
                userProfileService.findMyUserProfile())
                .orElseThrow();
    }

    public Project findMyProjectByUnicName(String unicName) {
        return repository.findByUnicNameAndOwner(
                unicName,
                userProfileService.findMyUserProfile())
                .orElseThrow();
    }


    public Project findProjectByExternalId(String externalId) {
        return repository.findByExternalId(externalId)
                .orElseThrow();
    }

    public Project findProjectByUnicName(String unicName) {
        return repository.findByUnicName(unicName)
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

    public Page<Project> findProjectImMember(PageRequest pageable) {
        return projectMemberRepository.findByUser(userProfileService.findMyUserProfile(), pageable).map(ProjectMember::getProject);
    }


    public Page<Project> findPublicProjects(String alias, PageRequest pageable) {
        if(StringUtil.isNullOrEmpty(alias)) {
            return repository.findByIsPublic(true, pageable);
        }else {
            return repository.findByIsPublicAndNameLike(true, "%"+alias+"%", pageable);
        }

    }



}
