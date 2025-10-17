package com.cumunity_appointments.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {
    
    private String name;

    private String email;

}
