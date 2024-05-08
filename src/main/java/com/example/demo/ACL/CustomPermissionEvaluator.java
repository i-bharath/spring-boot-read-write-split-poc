package com.example.demo.ACL;

import com.example.demo.entity.DataBox;
import com.example.demo.entity.User;
import com.example.demo.helpers.Roles;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {

        Object principal = authentication.getPrincipal();
        User user = (User) principal;

//        this piece is to demonstrate how we can access more information
        if (targetDomainObject instanceof DataBox) {
            DataBox dataBoxDto = (DataBox) targetDomainObject;
            System.out.println(dataBoxDto.getId());
            System.out.println(dataBoxDto.getTitle());
        }

        switch (permission.toString()) {
            case "READ":
                return true;
            case "WRITE":
                return true;
            case "VIEW_DATA_ALL":
                return user.getRole() == Roles.ADMIN.getValue();
            default:
                return false;
        }
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        // Not used in this example
        return false;
    }
}