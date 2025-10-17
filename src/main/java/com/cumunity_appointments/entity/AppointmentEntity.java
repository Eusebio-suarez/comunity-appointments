package com.cumunity_appointments.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.cumunity_appointments.enums.StatusEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name="appointments")
public class AppointmentEntity{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id",nullable=false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name="professional_id")
    private ProfessionalEntity professional;
    
    private LocalDate date;

    private LocalTime time;

    private StatusEnum status;
}
