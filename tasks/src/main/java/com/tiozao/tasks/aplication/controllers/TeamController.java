package com.tiozao.tasks.aplication.controllers;

import com.tiozao.tasks.aplication.controllers.request.InviteToTeamRequest;
import com.tiozao.tasks.domain.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/")
public class TeamController {

    @Autowired
    private TeamService teamService;

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
}
