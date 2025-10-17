package com.cumunity_appointments.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cumunity_appointments.dto.request.UserRequestDTO;
import com.cumunity_appointments.dto.response.UserResponseDTO;
import com.cumunity_appointments.entity.UserEntity;
import com.cumunity_appointments.enums.RolEnum;
import com.cumunity_appointments.repository.UserReposistory;

@Service
public class UserService {

    @Autowired
    private UserReposistory userReposistory;
    
    public UserResponseDTO register(UserRequestDTO userRequestDTO ){
        
        UserEntity user = UserEntity.builder()
            .email(userRequestDTO.getEmail())
            .name(userRequestDTO.getName())
            .password(userRequestDTO.getPassword())
            .phone(userRequestDTO.getPhone())
            .rol(RolEnum.USER)
            .build();
        
            
        UserEntity userRegister = userReposistory.save(user);

        return UserResponseDTO.builder()
            .email(userRegister.getEmail())
            .name(userRegister.getName())
            .build();
    }
}

