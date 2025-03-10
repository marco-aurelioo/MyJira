package com.tiozao.tasks.aplication.converter;

import com.tiozao.tasks.aplication.controllers.model.UserProfile;
import org.springframework.stereotype.Component;

@Component
public class UserProfileConverter {

    public UserProfile convertToDto(com.tiozao.tasks.domain.entity.UserProfile myUserProfile) {
        UserProfile dto = new UserProfile();
        dto.setName(myUserProfile.getName());
        dto.setUserId(myUserProfile.getExternalId());
        dto.setAvatar(myUserProfile.getAvatar());
        return dto;
    }



    public com.tiozao.tasks.domain.entity.UserProfile convertToEntity(UserProfile userProfile) {
        com.tiozao.tasks.domain.entity.UserProfile entity = new com.tiozao.tasks.domain.entity.UserProfile();
        entity.setName(userProfile.getName());
        entity.setExternalId(userProfile.getUserId());
        entity.setAvatar(userProfile.getAvatar());
        return entity;
    }
}
