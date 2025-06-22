package com.tiozao.tasks.aplication.converter;

import com.tiozao.tasks.aplication.controllers.model.ProjectParticipation;
import com.tiozao.tasks.domain.views.ProjectRequestParticipation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ProjectParticipationConverter {

    @Autowired
    private ProjectConverter projectConverter;

    public Page<ProjectParticipation> convertPage(Page<ProjectRequestParticipation> projectParticipationRequest) {
        return projectParticipationRequest.map(this::convertToDto);
    }

    private ProjectParticipation convertToDto(ProjectRequestParticipation projectRequestParticipation) {
        ProjectParticipation projectParticipation = new ProjectParticipation();
        projectParticipation.setQtdInvites(projectRequestParticipation.getQtdPendingInvites());
        projectParticipation.setQtdRequest(projectRequestParticipation.getQtdPendingRequests());
        projectParticipation.setProject(
                projectConverter.convertToDto(projectRequestParticipation.getProject()));
        return projectParticipation;

    }
}
