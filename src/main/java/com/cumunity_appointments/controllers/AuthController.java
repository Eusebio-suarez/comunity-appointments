package com.cumunity_appointments.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cumunity_appointments.dto.request.LoginRequestDTO;
import com.cumunity_appointments.dto.request.UserRequestDTO;
import com.cumunity_appointments.dto.response.LoginResponse;
import com.cumunity_appointments.dto.response.UserResponseDTO;
import com.cumunity_appointments.services.UserService;
import com.cumunity_appointments.utils.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users/auth")
public class AuthController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(@Valid @RequestBody UserRequestDTO userRequestDTO){

        UserResponseDTO user = userService.register(userRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.builder()
                .success(true)
                .message("exito")
                .data(user)
                .build()
            );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
    
        LoginResponse response = userService.tryLogin(loginRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.builder()
                .success(true)
                .message("exito")
                .data(response)
                .build()
            );
    }

}
