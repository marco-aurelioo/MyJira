package com.tiozao.tasks.domain.service.providers.useraccess;

import com.tiozao.tasks.domain.service.providers.useraccess.PermissionApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRolesService {

    @Autowired
    private PermissionApi permissionApi;


    public boolean addRole(String userId, String roleName){

        return permissionApi.addRole(roleName, userId);
    }









}
