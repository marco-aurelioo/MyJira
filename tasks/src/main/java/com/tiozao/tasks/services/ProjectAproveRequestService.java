package com.tiozao.tasks.services;

import com.tiozao.tasks.domain.entity.*;
import com.tiozao.tasks.domain.views.ProjectRequestParticipation;
import com.tiozao.tasks.repository.ProjectMemberRepository;
import com.tiozao.tasks.repository.ProjectRepository;
import com.tiozao.tasks.repository.ProjectRoleRepository;
import com.tiozao.tasks.repository.RequestParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
public class ProjectAproveRequestService {

    @Autowired
    private RequestParticipationRepository participationRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectMemberRepository projectMemberRepository;

    @Autowired
    private ProjectRoleRepository projectRoleRepository;

    @Autowired
    private UserProfileService profileService;
    public Page<ProjectRequestParticipation> findRequestsToMyProjects(Pageable pageable){
        Page<Object[]> sumary =  projectRepository.findMyProjectsWithRequestParticipation(
                profileService.findMyUserProfile().getId(),pageable);
        return sumary.map(this::convertProjectRequestParticipation);
    }

    public Page<RequestParticipation> findRequestsToMyProject(String projectId, Pageable pageRequest) {
        return participationRepository.findByProjectAndStatus(
                projectRepository.findByExternalId(projectId).orElseThrow(),
                ParticipationStatus.PENDING,
                pageRequest);
    }

    public RequestParticipation updateRequestsToMyProject(String projectId, Long inviteId, String status) {
        RequestParticipation request = participationRepository.findById(inviteId).orElseThrow();
        UserProfile myprofile = profileService.findMyUserProfile();

        if(!request.getProject().getExternalId().equals(projectId)) {
            throw new RuntimeException("projeto invalido");
        }
        if(!request.getProject().getOwner().equals(myprofile)){
            throw new RuntimeException("dono do projeto invalido");
        }
        switch (status){
            case "APPROVE":
                ProjectMember projectMember = new ProjectMember();
                projectMember.setProject(request.getProject());
                projectMember.setUser(request.getRequestingUser());
                projectMember.setRole(projectRoleRepository.findByName("Developer"));
                projectMemberRepository.save(projectMember);
                request.setStatus(ParticipationStatus.ACCEPTED);
                participationRepository.save(request);
                //deve mandar uma msg para o ususario que foi aceito
                return request;
            case "REJECT":
                request.setStatus(ParticipationStatus.REJECTED);
                participationRepository.save(request);
                //deve mandar uma msg para o ususario que nao foi aceito
                return request;
            default:
                throw new RuntimeException("status invalido");
        }
    }

    private ProjectRequestParticipation convertProjectRequestParticipation( Object[] values) {
        Project project = new Project();
        project.setExternalId(values[0].toString());
        project.setName(values[1].toString());
        project.setUnicName(values[2].toString());
        project.setDescription(values[3].toString());
        return new ProjectRequestParticipation(
                project,
                Integer.parseInt(values[4].toString()),
                Integer.parseInt(values[5].toString()));
    }



}
