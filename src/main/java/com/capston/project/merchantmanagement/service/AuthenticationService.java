package com.capston.project.merchantmanagement.service;

import com.capston.project.merchantmanagement.config.CustomAuthenticationProvider;
import com.capston.project.merchantmanagement.dtos.LoginDTO;
import com.capston.project.merchantmanagement.dtos.RegisterDTO;
import com.capston.project.merchantmanagement.entities.Users;
import com.capston.project.merchantmanagement.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository repo;
    @Autowired
    private CustomAuthenticationProvider authenticationManager;
    @Autowired
    private PasswordEncoder encoder;

    public Users signUp(RegisterDTO registerDTO){
        Users user = new Users();
        user.setName(registerDTO.getName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(encoder.encode(registerDTO.getPassword()));
        return repo.save(user);
    }

    public Users authenticate(LoginDTO login){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(),login.getPassword()));
        return repo.findByEmail(login.getEmail()).orElseThrow();
    }
}
