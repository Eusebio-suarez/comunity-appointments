package com.cumunity_appointments.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;



import com.cumunity_appointments.enums.StatusEnum;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppointmentResponseDTO {

    private String userName;

    private String professionalName;
        
    private LocalDate date;

    private LocalTime time;

    private StatusEnum status;
}
