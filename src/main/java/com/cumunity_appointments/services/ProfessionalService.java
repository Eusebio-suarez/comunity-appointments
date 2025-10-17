package com.cumunity_appointments.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cumunity_appointments.dto.request.ProfessionalRequestDTO;
import com.cumunity_appointments.dto.response.UserResponseDTO;
import com.cumunity_appointments.entity.ProfessionalEntity;
import com.cumunity_appointments.entity.UserEntity;
import com.cumunity_appointments.enums.RolEnum;
import com.cumunity_appointments.repository.ProfessionalRepository;
import com.cumunity_appointments.repository.UserReposistory;

@Service
public class ProfessionalService {

    @Autowired
    private UserReposistory userReposistory;

    @Autowired
    private ProfessionalRepository professionalRepository;
    
    public UserResponseDTO register(ProfessionalRequestDTO professionalRequestDTO){

        UserEntity user = UserEntity.builder()
            .email(professionalRequestDTO.getEmail())
            .name(professionalRequestDTO.getName())
            .password(professionalRequestDTO.getPassword())
            .phone(professionalRequestDTO.getPhone())
            .rol(RolEnum.PROFESSIONAL)
            .build();
            
        UserEntity userRegister = userReposistory.save(user);
        
        ProfessionalEntity professional = ProfessionalEntity.builder()
            .user(userRegister)
            .specialty(professionalRequestDTO.getSpecialty())
            .build();

        ProfessionalEntity professionalRegister = professionalRepository.save(professional);

        return UserResponseDTO.builder()
            .email(professionalRegister.getUser().getEmail())
            .name(professionalRegister.getUser().getName())
            .build();
    }

}
