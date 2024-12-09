package com.tiozao.tasks.domain.service;

import com.tiozao.tasks.aplication.controllers.request.RespondInviteRequest;
import com.tiozao.tasks.aplication.controllers.response.ProjectResponse;
import com.tiozao.tasks.domain.entity.*;
import com.tiozao.tasks.domain.service.model.InvitePerson;
import com.tiozao.tasks.domain.service.model.InviteToProject;
import com.tiozao.tasks.domain.service.providers.messages.MessageService;
import com.tiozao.tasks.resources.repositories.InvitePersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamService {

    @Autowired
    private MessageService messageService;

    @Autowired
    private PersonService personService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private ProjectService projectService;

    @Autowired
    private InvitePersonRepository invitePersonRepository;

    private static final String BASE_URL = "http://localhost:4200/messages/invite-project";

    public Boolean invitePerson(String pessoaFromId, String pessoaToId, String organizationId, String projectId) {
        Map<String,String> atributos = new HashMap<String,String>();
        OrganizationEntity organization = organizationService.findORganizationById(organizationId);
        ProjectEntity projeto = projectService.findByExternalId(projectId);

        atributos.put("organization",organization.getName());
        atributos.put("project",projeto.getProjectName());
        atributos.put("linkAccept",generateLinkAccept(pessoaToId,organization.getExternalId(),projectId));
        atributos.put("linkDecline",generateLinkIgnore(pessoaToId,organization.getExternalId(),projectId));


        String externalIdmsg = messageService.sendInternalMessage(
                pessoaFromId,
                pessoaToId,
                "Convite para projeto" ,
                "Template",
                organization.getExternalId(),
                atributos);

        InvitePersonEntity entity = new InvitePersonEntity();
        entity.setFromPersonId(pessoaFromId);
        entity.setProject(projeto);
        entity.setExternalPersonId(pessoaToId);
        entity.setStatus(STATUS_INVITE.ENVIADO);
        entity.setTemplate("Template");
        invitePersonRepository.save(entity);

        return true;
    }

    public Page<InvitePerson> findInvitePerson(String from, String pessoaId, String organizationId, String projectId, Pageable page) {
        ProjectEntity projectEntity = projectService.findByExternalId(projectId);
        Page<PersonEntity> pagina = personService.findPersonLikeName(null,page);
        List<String> listaDeUsuarios = pagina.getContent().stream().map(PersonEntity::getUserId).collect(Collectors.toList());
        Map<String, STATUS_INVITE> invites = invitePersonRepository
                .findByProjectAndExternalPersonIdIn(projectEntity,listaDeUsuarios)
                .stream().collect(
                Collectors.toMap(InvitePersonEntity::getExternalPersonId, InvitePersonEntity::getStatus));
        return pagina.map(person ->{
            InvitePerson dto = new InvitePerson();
            dto.setUserId(person.getUserId());
            dto.setName(person.getName());
            dto.setAvatar(person.getAvatar());
            dto.setProjectId(projectEntity.getExternalId());
            dto.setStatus(
                    invites
                            .getOrDefault(
                                    person.getUserId(),
                                    STATUS_INVITE.NAO_ENVIADO)
                    .toString());
            return dto;

        });
    }

    private String generateLink(String pessoaToId, String organization, String project, boolean accepted) {
        return BASE_URL + "?org=" + organization + "&project=" + project + "&pers=" + pessoaToId + "&st=" + accepted;
    }

    private String generateLinkAccept(String pessoaToId, String organization, String project) {
        return generateLink(pessoaToId, organization, project, false);
    }

    private String generateLinkIgnore(String pessoaToId, String organization, String project) {
        return generateLink(pessoaToId, organization, project, true);
    }


    public Page<InvitePerson> findInviteProject(String from, String organizationId, String projectIs, Pageable pageable) {
        return null;
    }

    public Page<InviteToProject> findMyInvites(String myExternalId, Pageable pageable) {
        Page<InvitePersonEntity> page =
                invitePersonRepository
                        .findByExternalPersonIdAndStatusIn(
                                myExternalId,
                                List.of(STATUS_INVITE.ENVIADO, STATUS_INVITE.LIDO),
                                pageable);
        return page.map( item -> convertEntityToDto(item));

    }

    private InviteToProject convertEntityToDto(InvitePersonEntity item) {
        InviteToProject invite = new InviteToProject();
        invite.setInviteId(item.getId());
        invite.setProjectName(item.getProject().getProjectName());
        invite.setProjectId(item.getProject().getExternalId());
        PersonEntity person = personService.findPersonByUserId(item.getFromPersonId());
        invite.setUserName(person.getName());
        invite.setUserId(person.getUserId());
        return invite;
    }

    public Page<ProjectResponse> findMyProjects(String myExternalId, Pageable pageable) {
        Page<ProjectEntity> projectsPage = personService.findMyProject(myExternalId, pageable);
        return projectsPage.map(project -> {
            return new ProjectResponse(project);
        });
    }

    public Boolean respondInvite(String myExternalId, String inviteId, RespondInviteRequest request) {
        InvitePersonEntity invitePerson = invitePersonRepository.findById(Integer.parseInt(inviteId)).orElseThrow();
        if(!invitePerson.getExternalPersonId().equals(myExternalId)){
            throw new IllegalArgumentException("Invite invalido!");
        }
        if(request.getAccept()) {
            ProjectEntity projeto = invitePerson.getProject();
            personService.incluiProjeto(myExternalId,projeto);
            invitePerson.setStatus(STATUS_INVITE.ACEITO);
        }else{
            invitePerson.setStatus(STATUS_INVITE.NEGADO);
        }
        invitePersonRepository.save(invitePerson);
        return true;
    }

    public InviteToProject getInvite(String myExternalId, String inviteId) {
        InvitePersonEntity invitePerson = invitePersonRepository.findById(Integer.parseInt(inviteId)).orElseThrow();
        if(!invitePerson.getExternalPersonId().equals(myExternalId)){
            throw new IllegalArgumentException("Invite invalido!");
        }
        return convertEntityToDto(invitePerson);
    }

}
