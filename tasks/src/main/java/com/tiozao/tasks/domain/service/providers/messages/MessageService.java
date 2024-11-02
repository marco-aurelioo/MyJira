package com.tiozao.tasks.domain.service.providers.messages;

import com.tiozao.tasks.domain.service.providers.messages.persist.InternalMessageEntity;
import com.tiozao.tasks.domain.service.providers.messages.persist.InternalMessageRepository;
import com.tiozao.tasks.domain.service.providers.messages.persist.TemplateInternalMessageEntity;
import com.tiozao.tasks.domain.service.providers.messages.persist.TemplateInternalMessageRepository;
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

    public boolean sendInternalMessage(String userFrom, String userTo, String title, String template, String owner, Map<String,String> atributes){
        InternalMessageEntity msg = new InternalMessageEntity();
        msg.setAttributesMap(atributes);
        msg.setFrom(userFrom);
        msg.setTo(userTo);
        msg.setTemplate(templateRepository.findByTemplateAndOwner(template,owner).orElseThrow());
        msg.setTitle(title);
        messageRepository.save(msg);
        return true;
    }

    public String createTemplate(String content, String owner){
        TemplateInternalMessageEntity templateEntity = new TemplateInternalMessageEntity();
        templateEntity.setTemplate(content);
        templateEntity.setOwner(owner);
        templateEntity.setExternalId(UUID.randomUUID().toString());
        templateEntity = templateRepository.save(templateEntity);
        return templateEntity.getExternalId();
    }


}
