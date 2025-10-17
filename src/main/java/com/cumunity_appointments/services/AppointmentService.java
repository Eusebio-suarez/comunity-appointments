package com.cumunity_appointments.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cumunity_appointments.dto.request.AppointmentRequestDTO;
import com.cumunity_appointments.dto.request.UpdateAppointmentDTO;
import com.cumunity_appointments.dto.response.AppointmentResponseDTO;
import com.cumunity_appointments.entity.AppointmentEntity;
import com.cumunity_appointments.entity.ProfessionalEntity;
import com.cumunity_appointments.entity.UserEntity;
import com.cumunity_appointments.enums.StatusEnum;
import com.cumunity_appointments.repository.AppointmentRepository;
import com.cumunity_appointments.repository.ProfessionalRepository;
import com.cumunity_appointments.repository.UserReposistory;

@Service
public class AppointmentService {
    
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserReposistory userReposistory;

    @Autowired
    private ProfessionalRepository professionalRepository;
    

    public List<AppointmentResponseDTO> getAppointments(){

        return appointmentRepository.findAll().stream()
            .map(appointment -> AppointmentResponseDTO.builder()
                .id(appointment.getId())
                .userName(appointment.getUser().getName())
                .professionalName(appointment.getProfessional().getUser().getName())
                .date(appointment.getDate())
                .time(appointment.getTime())
                .status(appointment.getStatus())
                .build()
            )
            .toList();
            
    }


    public AppointmentEntity update(UpdateAppointmentDTO updateAppointmentDTO){

        Optional<AppointmentEntity> appointmentOptional = appointmentRepository.findById(updateAppointmentDTO.getId());

        if(appointmentOptional.isEmpty()){

            throw  new RuntimeException("no se encontro el la cita");
        }

        AppointmentEntity appointmentEntity = appointmentOptional.get();

        appointmentEntity.setStatus(updateAppointmentDTO.getStatus());


        return appointmentRepository.save(appointmentEntity);
    }

    public List<AppointmentResponseDTO>getByProfessionalId(Long id){

        Optional<ProfessionalEntity> professionalOptional = professionalRepository.findById(id);

        ProfessionalEntity professional = professionalOptional.get();

        if(professionalOptional.isEmpty()){
            throw  new RuntimeException("no se encontro el prefessional");
        }

        return appointmentRepository.findByProfessional(professional).stream()
            .map(appointment -> AppointmentResponseDTO.builder()
                .id(appointment.getId())
                .userName(appointment.getUser().getName())
                .professionalName(appointment.getProfessional().getUser().getName())
                .date(appointment.getDate())
                .time(appointment.getTime())
                .status(appointment.getStatus())
                .build()
            )
            .toList();

    }

    public AppointmentResponseDTO registerAppointment(AppointmentRequestDTO appointmentRequestDTO){
        Optional<UserEntity> userOptional = userReposistory.findById(appointmentRequestDTO.getUserId());
        
        if(userOptional.isEmpty()){

            throw new RuntimeException("no se encontro el usuario");
        }

        UserEntity user = userOptional.get();

        Optional<ProfessionalEntity> professionalOptional = professionalRepository.findById(appointmentRequestDTO.getProfessionalId());

        if(professionalOptional.isEmpty()){
            throw  new RuntimeException("no se encontro el prefessional");
        }
        
        ProfessionalEntity professional = professionalOptional.get();

        if(appointmentRepository.existsByProfessionalAndDateAndTime(professional, appointmentRequestDTO.getDate(), appointmentRequestDTO.getTime())){

            throw  new RuntimeException("error cita duplicada");

        }

        AppointmentEntity appointment = AppointmentEntity.builder()
            .professional(professional)
            .user(user)
            .date(appointmentRequestDTO.getDate())
            .time(appointmentRequestDTO.getTime())
            .status(StatusEnum.PENDING)
            .build();

        AppointmentEntity appointmentRegister = appointmentRepository.save(appointment);
        

        return AppointmentResponseDTO.builder()
            .userName(appointmentRegister.getUser().getEmail())
            .professionalName(appointmentRegister.getProfessional().getUser().getName())
            .date(appointmentRegister.getDate())
            .time(appointmentRegister.getTime())
            .status(appointmentRegister.getStatus())
            .build();
    
    }

}
