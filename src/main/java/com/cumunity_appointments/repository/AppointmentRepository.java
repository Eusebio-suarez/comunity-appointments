package com.cumunity_appointments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cumunity_appointments.entity.AppointmentEntity;
import com.cumunity_appointments.entity.ProfessionalEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;




@Repository
public interface  AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    
    boolean existsByProfessionalAndDateAndTime(ProfessionalEntity professional,LocalDate date,LocalTime time);

    List<AppointmentEntity> findByProfessional(ProfessionalEntity professional);
}
