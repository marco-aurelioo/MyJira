package com.tiozao.tasks.services;

import com.tiozao.tasks.domain.entity.Project;
import com.tiozao.tasks.domain.entity.ProjectRequest;
import com.tiozao.tasks.domain.entity.RequestStatus;
import com.tiozao.tasks.domain.entity.UserProfile;
import com.tiozao.tasks.repository.ProjectRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectRequestService {

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private PrivateProjectService projectService;

    @Autowired
    private ProjectRequestRepository projectRequestRepository;

    public Page<ProjectRequest> findMyCreatedProjectRequests(Pageable pageable, List<RequestStatus> status){
        UserProfile myProfile = userProfileService.findMyUserProfile();
        return projectRequestRepository
                .findBySenderAndStatusIn(
                        myProfile,
                        status,
                        pageable);
    }

    public Page<ProjectRequest> findProjectRequestsSendsToMe(Pageable pageable, List<RequestStatus> status){
        UserProfile myProfile = userProfileService.findMyUserProfile();
        return projectRequestRepository
                .findByReceiverAndStatusIn(
                        myProfile,
                        status,
                        pageable);
    }


    public ProjectRequest createRequestToProject(String projectId, String mensagem){
        Project project = projectService.findProjectByExternalId(projectId);
        ProjectRequest projectRequest = createProjectRequest(
                userProfileService.findMyUserProfile(),
                project.getOwner(), project, mensagem
        );
        return  projectRequestRepository.save(projectRequest);
    }

    public ProjectRequest createRequestToInvite(String userExternalId, String projectId, String mensagem){

        UserProfile userConvided = userProfileService.findUserByExternalId(userExternalId);
        Project project = projectService.findMyProjectByExternalId(projectId);
        ProjectRequest projectRequest = createProjectRequest(
                userProfileService.findMyUserProfile(),
                userConvided, project, mensagem
        );

        return  projectRequestRepository.save(projectRequest);
    }


    private ProjectRequest createProjectRequest(
            UserProfile sender,
            UserProfile receiver,
            Project project,
            String mensagem){
        ProjectRequest projectRequest = new ProjectRequest();
        projectRequest.setReceiver(receiver); //será que poderia mandar para varios com a permissão de leitura de requests;
        projectRequest.setProject(project);
        projectRequest.setSender(sender); 
        projectRequest.setMessage(mensagem);
        projectRequest.setStatus(RequestStatus.PENDING);
        return projectRequest;
    }

}
