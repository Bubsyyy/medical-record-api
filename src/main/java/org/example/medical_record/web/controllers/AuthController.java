package org.example.medical_record.web.controllers;

import org.example.medical_record.config.JwtUtil;
import org.example.medical_record.user.UserService;
import org.example.medical_record.web.dto.AuthRequest;
import org.example.medical_record.web.dto.AuthResponse;
import org.example.medical_record.web.dto.PasswordResetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;


    @Autowired
    public AuthController(AuthenticationManager authenticationManager,UserService userService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService=userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));


        UserDetails user = userService.loadUserByUsername(request.getUsername());
        String token = jwtUtil.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    //TODO
    @PostMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestBody PasswordResetRequest request) {

        String username = request.getUsername();


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, request.getCurrentPassword()));

        String newPassword = request.getNewPassword();

        userService.resetUserPassword(username, newPassword);

        return ResponseEntity.ok().build();
    }





}
