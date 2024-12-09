package com.tiozao.tasks.aplication.controllers;

import com.tiozao.tasks.aplication.controllers.request.InviteToTeamRequest;
import com.tiozao.tasks.aplication.controllers.request.RespondInviteRequest;
import com.tiozao.tasks.aplication.controllers.response.ProjectResponse;
import com.tiozao.tasks.aplication.dtos.PersonDto;
import com.tiozao.tasks.assembler.converters.PersonModelConverter;
import com.tiozao.tasks.domain.service.PersonService;
import com.tiozao.tasks.domain.service.TeamService;
import com.tiozao.tasks.domain.service.model.InvitePerson;
import com.tiozao.tasks.domain.service.model.InviteToProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private PersonModelConverter converter;

    @PostMapping("/team/{organization}/projects/{project}/invites")
    public ResponseEntity<Boolean> sendRequestTeam(
            @RequestBody InviteToTeamRequest invite,
            @PathVariable String organization,
            @PathVariable String project,
            Principal principal){
        String from = ((JwtAuthenticationToken) principal).getToken().getClaim("sub");
        return ResponseEntity.ok(teamService.invitePerson(
                from,
                invite.getPessoaId(),
                organization,
                project));
    }

    @GetMapping("/team/{organization}/projects/{project}/invites")
    public ResponseEntity<Page<InvitePerson>> getPessoasToRequestTeam(

            @PathVariable String organizationId,
            @PathVariable String projectIs,
            Pageable pageable,
            Principal principal){
        String from = ((JwtAuthenticationToken) principal).getToken().getClaim("sub");
        return ResponseEntity.ok(
                   teamService.findInviteProject(
                    from,
                    organizationId,
                    projectIs,
                    pageable));
    }




    @GetMapping("/team/my-invites")
    public ResponseEntity<Page<InviteToProject>> getMyAcceptedsInvitesTeam(
            Pageable pageable,
            Principal principal){
        String myExternalId = ((JwtAuthenticationToken) principal).getToken().getClaim("sub");
        return ResponseEntity.ok(
                teamService.findMyInvites(
                        myExternalId,pageable));
    }

    @PostMapping("/team/my-invites/{inviteId}")
    public ResponseEntity<Boolean> respondIbvite(
            @PathVariable("inviteId") String inviteId,
            @RequestBody RespondInviteRequest request,
            Principal principal){
        String myExternalId = ((JwtAuthenticationToken) principal).getToken().getClaim("sub");
        return ResponseEntity.ok(
                teamService.respondInvite(
                        myExternalId,inviteId,request));
    }

    @GetMapping("/team/my-invites/{inviteId}")
    public ResponseEntity<InviteToProject> respondIbvite(
            @PathVariable("inviteId") String inviteId,
            Principal principal){
        String myExternalId = ((JwtAuthenticationToken) principal).getToken().getClaim("sub");
        return ResponseEntity.ok(
                teamService.getInvite(
                        myExternalId,inviteId));
    }

    @GetMapping("/team/my-projects")
    public ResponseEntity<Page<ProjectResponse>> getMyProjects(
            Pageable pageable,
            Principal principal){
        String myExternalId = ((JwtAuthenticationToken) principal).getToken().getClaim("sub");
        return ResponseEntity.ok(
                teamService.findMyProjects(
                        myExternalId,pageable));
    }

}
