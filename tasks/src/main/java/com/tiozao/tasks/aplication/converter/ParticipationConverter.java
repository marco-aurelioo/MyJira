package com.tiozao.tasks.aplication.converter;

import com.tiozao.tasks.aplication.controllers.model.Participar;
import com.tiozao.tasks.domain.entity.Project;
import com.tiozao.tasks.domain.entity.RequestParticipation;
import com.tiozao.tasks.domain.views.ProjectRequestParticipation;
import com.tiozao.tasks.repository.ProjectRepository;
import com.tiozao.tasks.repository.UserProfileRepository;
import com.tiozao.tasks.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.Date;

@Component
public class ParticipationConverter {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private ProjectConverter projectConverter;

    @Autowired
    private UserProfileConverter userProfileConverter;

    public RequestParticipation convertToEntity(Participar dto){
        RequestParticipation entity = new RequestParticipation();
        entity.setMessage(dto.getComentario());
        Project project = projectRepository.findByExternalId(dto.getProjectId()).orElseThrow();
        entity.setProject(project);
        entity.setRequestingUser(userProfileService.findMyUserProfile());
        entity.setApprovingUser(project.getOwner());
        return entity;
    }

    public Participar convertToDto(RequestParticipation requestToProject) {
        Participar dto = new Participar();
        dto.setId(requestToProject.getId());
        dto.setComentario(requestToProject.getMessage());
        dto.setProjectId(requestToProject.getProject().getExternalId());
        dto.setProject(projectConverter.convertToDto(requestToProject.getProject()));
        dto.setStatus(requestToProject.getStatus().name());

        dto.setFeedback("");
        dto.setDataAtualizacao(Date.from(requestToProject.getUpdatedAt().atZone(ZoneId.systemDefault()).toInstant()));
        dto.setDataCriacao(Date.from(requestToProject.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant()));

        dto.setUsuario(userProfileConverter.convertToDto(requestToProject.getRequestingUser()));

        return dto;
    }

    public Page<Participar> convertPage(Page<RequestParticipation> page) {
        return page.map(this::convertToDto);
    }
}
