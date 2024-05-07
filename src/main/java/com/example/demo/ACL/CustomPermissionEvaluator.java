package com.example.demo.ACL;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        System.out.println("i was hereeeeee");
        switch (permission.toString()) {
            case "READ":
                return true;
            case "WRITE":
                return true;
            case "RAND":
                System.out.println("i was hereeeeee");
//                Integer role = authentication.getRole();
                return false;
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