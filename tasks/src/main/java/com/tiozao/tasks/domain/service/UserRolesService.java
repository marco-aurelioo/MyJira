package com.tiozao.tasks.domain.service;

import com.tiozao.tasks.domain.service.providers.PermissionApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserRolesService {

    @Autowired
    private PermissionApi permissionApi;


    public boolean addRole(String userId, String roleName){

        return permissionApi.addRole(roleName, userId);
    }









}
