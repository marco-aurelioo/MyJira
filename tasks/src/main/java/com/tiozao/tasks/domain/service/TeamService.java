package com.tiozao.tasks.domain.service;

import com.tiozao.tasks.domain.service.providers.messages.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TeamService {

    @Autowired
    private MessageService messageService;

    private static final String BASE_URL = "http://localhost:4200/messages/invite-project";

    public Boolean invitePerson(String pessoaFromId, String pessoaToId, String organization, String project) {
        Map<String,String> atributos = new HashMap<String,String>();

        atributos.put("organization",organization);
        atributos.put("project",project);
        atributos.put("linkAccept",generateLinkAccept(pessoaToId,organization,project));
        atributos.put("linkDecline",generateLinkIgnore(pessoaToId,organization,project));

        messageService.sendInternalMessage(
                pessoaFromId,
                pessoaToId,
                "Convite para projeto" ,
                "Template",
                organization,
                atributos);

        return true;
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


}
