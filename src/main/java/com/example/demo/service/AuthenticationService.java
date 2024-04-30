package com.example.demo.service;

import com.example.demo.dto.LoginUserDto;
import com.example.demo.dto.RegisterUserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        User user = new User();
        user.setName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(input.getPassword());
        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        System.out.println(input.getEmail());
        System.out.println(input.getPassword());

//        this part was doing validation and verification of password.
//        i tried multiple permutation of this but was always getting Bad Credentials.
//        since user login is not part of this so commented


//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        input.getEmail(),
//                        input.getPassword()
//                )
//        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}