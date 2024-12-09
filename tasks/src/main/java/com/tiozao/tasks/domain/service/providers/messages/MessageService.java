package com.tiozao.tasks.domain.service.providers.messages;

import com.tiozao.tasks.domain.service.providers.messages.persist.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class MessageService {

    @Autowired
    private TemplateInternalMessageRepository templateRepository;

    @Autowired
    private InternalMessageRepository messageRepository;

    public String sendInternalMessage(String userFrom, String userTo, String title, String template, String owner, Map<String,String> atributes){
        InternalMessageEntity msg = new InternalMessageEntity();
        msg.setAttributesMap(atributes);
        msg.setFromUser(userFrom);
        msg.setToUser(userTo);
        msg.setTemplate(templateRepository.findByTemplateAndOwner(template,owner).orElseThrow());
        msg.setTitle(title);
        msg.setExternalId(UUID.randomUUID().toString());
        msg.setStatus(STATUS_MESSAGE.ENVIADO);
        messageRepository.save(msg);
        return msg.getExternalId();
    }

    public String createTemplate(String templateName, String content, String owner){
        TemplateInternalMessageEntity templateEntity = new TemplateInternalMessageEntity();
        templateEntity.setTemplate(templateName);
        templateEntity.setOwner(owner);
        templateEntity.setContent(content);
        templateEntity.setExternalId(UUID.randomUUID().toString());
        templateEntity = templateRepository.save(templateEntity);
        return templateEntity.getExternalId();
    }


}
