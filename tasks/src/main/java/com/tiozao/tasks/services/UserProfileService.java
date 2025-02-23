package com.tiozao.tasks.services;

import com.tiozao.tasks.aplication.saas.userprofile.KeycloakService;
import com.tiozao.tasks.aplication.saas.userprofile.model.SessionKeyCloak;
import com.tiozao.tasks.domain.entity.UserProfile;
import com.tiozao.tasks.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository repository;

    @Autowired
    private KeycloakService keycloakService;

    public UserProfile findMyUserProfile() {
        SessionKeyCloak sessionKeyCloak = keycloakService.getSessionKeyCloak();
        if(sessionKeyCloak.getActive()) {
            return repository.findByExternalId(sessionKeyCloak.getSub())
                    .orElseGet(() -> repository.save(createProfileFromSession(sessionKeyCloak)));
        }
        throw new IllegalStateException();
    }

    public UserProfile updateProfile(UUID externalId, UserProfile userProfile) {
        SessionKeyCloak sessionKeyCloak = keycloakService.getSessionKeyCloak();
        if(sessionKeyCloak.getActive() && externalId.toString().equals(sessionKeyCloak.getSub())){
            UserProfile profile =findUserByExternalId(externalId.toString());
            profile.setAvatar(userProfile.getAvatar());
            profile.setName((userProfile.getName()));
            return repository.save(profile);
        }
        throw new IllegalStateException();
    }

    private UserProfile createProfileFromSession(SessionKeyCloak sessionKeyCloak) {
        UserProfile userProfile = new UserProfile();
        userProfile.setName(sessionKeyCloak.getUsername());
        userProfile.setExternalId(sessionKeyCloak.getSub());
        return userProfile;
    }

    public UserProfile findUserByExternalId(String userId) {
        return repository
                .findByExternalId(userId)
                .orElseThrow();
    }

    public Page<UserProfile> findByUserNameLike(String name, Pageable pageable) {
        return repository.findByNameLike("%"+name+"%", pageable);
    }
}
