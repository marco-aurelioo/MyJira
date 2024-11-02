package com.tiozao.tasks.assembler.converters;

import com.tiozao.tasks.assembler.converters.models.ProjectIn;
import com.tiozao.tasks.assembler.converters.models.ProjectOut;
import com.tiozao.tasks.domain.entity.ProjectEntity;
import com.tiozao.tasks.domain.service.OrganizationService;
import com.tiozao.tasks.domain.service.PersonService;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class ProjectConverter extends Converter<ProjectIn, ProjectOut> {


    private static PersonService personService;

    private static OrganizationService organizationService;


    public ProjectConverter(PersonService personService, OrganizationService organizationService) {
        super(ProjectConverter::originDomain, ProjectConverter::domainOrigin);
        ProjectConverter.personService = personService;
        ProjectConverter.organizationService = organizationService;
    }

    private static ProjectIn domainOrigin(ProjectOut projectOut) {
        return null;
    }

    private static ProjectOut originDomain(ProjectIn projectIn) {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectName(projectIn.getProjectDto().getProjectName());
        projectEntity.setDescription(projectIn.getProjectDto().getDescription());
        projectEntity.setOwner(
                personService.findPersonByUserId(
                        ((JwtAuthenticationToken) projectIn.getPrincipal()).getToken().getClaim("sub")));
        projectEntity.setOrganization(organizationService.findORganizationByName(projectIn.getOrganizations()));
        return new ProjectOut(projectEntity);

    }
}
