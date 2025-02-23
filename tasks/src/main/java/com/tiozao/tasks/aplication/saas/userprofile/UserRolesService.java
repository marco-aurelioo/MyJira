package com.tiozao.tasks.aplication.saas.userprofile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRolesService {

    @Autowired
    private KeycloakClient permissionApi;

    public boolean addRole(String userId, String roleName){
        return permissionApi.addRole(roleName, userId);
    }

}
