package com.cumunity_appointments.dto.request;

import com.cumunity_appointments.enums.StatusEnum;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateAppointmentDTO {
    
    private Long id;

    private StatusEnum status;
}
