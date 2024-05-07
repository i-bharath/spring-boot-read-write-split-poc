package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    @PreAuthorize("hasPermission(com.example.demo.entity.User, 'RAND')")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @PreAuthorize("hasPermission(com.example.demo.entity.User, 'READ')")
    @GetMapping("say-hello")
    public ResponseEntity<List> sayHello() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        List<GrantedAuthority> grantedAuthorities =
//                (List<GrantedAuthority>) authentication.getAuthorities();
        List<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
//        return ResponseEntity.ok(List.of("hello","brother!"));
        return ResponseEntity.ok(roles);
    }

//    @GetMapping("say-hello")
//    public ResponseEntity<String> sayHello() {
//        return ResponseEntity.ok("Hello World");
//    }

    @GetMapping("/")
    public ResponseEntity<List<User>> allUsers() {
        List <User> users = userService.allUsers();

        return ResponseEntity.ok(users);
    }
}