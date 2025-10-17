package com.cumunity_appointments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cumunity_appointments.entity.AppointmentEntity;

@Repository
public interface  AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    
}
