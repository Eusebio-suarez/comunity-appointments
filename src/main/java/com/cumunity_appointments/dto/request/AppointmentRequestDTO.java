package com.cumunity_appointments.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.Future;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppointmentRequestDTO {
    
    private Long userId;

    private Long professionalId;
    
    @Future
    private LocalDate date;

    @Future
    private LocalTime time;
}
