package com.capston.project.merchantmanagement.controller;

import com.capston.project.merchantmanagement.dtos.LoginDTO;
import com.capston.project.merchantmanagement.dtos.LoginResponseDTO;
import com.capston.project.merchantmanagement.dtos.RegisterDTO;
import com.capston.project.merchantmanagement.entities.Users;
import com.capston.project.merchantmanagement.service.AuthenticationService;
import com.capston.project.merchantmanagement.service.JwtService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RolesAllowed("USER_ADMIN")
public class LoginController {

    @Autowired
    private AuthenticationService service;

    @Autowired
    private JwtService jwt;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO login){
        Users user = service.authenticate(login);
        String token = jwt.generateToken(user);
        LoginResponseDTO dto = new LoginResponseDTO();
        dto.setToken(token);
        dto.setExpiresIn(jwt.getExpirationTime());
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/signup")
    public ResponseEntity<Users> register(@RequestBody RegisterDTO registerDTO){
        Users user = service.signUp(registerDTO);
        return ResponseEntity.ok(user);
    }
}
