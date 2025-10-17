package com.cumunity_appointments.dto.response;

import com.cumunity_appointments.enums.RolEnum;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private Long id;
    private RolEnum rol;
}
