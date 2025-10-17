package com.cumunity_appointments.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cumunity_appointments.dto.request.ProfessionalRequestDTO;
import com.cumunity_appointments.dto.response.UserResponseDTO;
import com.cumunity_appointments.services.ProfessionalService;
import com.cumunity_appointments.utils.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    

    @Autowired
    private ProfessionalService professionalService;

    @PostMapping("/professionals/register")
    public ResponseEntity<ApiResponse<?>> register(@Valid @RequestBody ProfessionalRequestDTO professionalRequestDTO){

        UserResponseDTO user = professionalService.register(professionalRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.builder()
                .success(true)
                .message("exito")
                .data(user)
                .build()
            );
    }
}
