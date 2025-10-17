package com.cumunity_appointments.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cumunity_appointments.dto.request.AppointmentRequestDTO;
import com.cumunity_appointments.dto.request.UpdateAppointmentDTO;
import com.cumunity_appointments.dto.response.AppointmentResponseDTO;
import com.cumunity_appointments.entity.AppointmentEntity;
import com.cumunity_appointments.services.AppointmentService;
import com.cumunity_appointments.utils.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/appoinments")
public class AppointmentController {
    
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("")
    public ResponseEntity<ApiResponse<?>> getAppointments(){

        List<AppointmentResponseDTO> appointmens = appointmentService.getAppointments();

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponse.builder()
                .success(true)
                .message("exito")
                .data(appointmens)
                .build()
            );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getAppointmentsByProfessionalId(@RequestParam("id") Long id){

        List<AppointmentResponseDTO> appointmens = appointmentService.getByProfessionalId(id);

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponse.builder()
                .success(true)
                .message("exito")
                .data(appointmens)
                .build()
            );
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>>registerAppointmen(@Valid @RequestBody AppointmentRequestDTO appointmentRequestDTO){

        AppointmentResponseDTO appointment = appointmentService.registerAppointment(appointmentRequestDTO);


        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.builder()
                .success(true)
                .message("exito")
                .data(appointment)
                .build()
            );
    }


    @PutMapping("/update")
    public ResponseEntity<ApiResponse<?>>registerAppointmen(@Valid @RequestBody UpdateAppointmentDTO updateAppointmentDTO){

        AppointmentEntity response = appointmentService.update(updateAppointmentDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.builder()
                .success(true)
                .message("exito")
                .data(response.getStatus())
                .build()
            );
    }

}
