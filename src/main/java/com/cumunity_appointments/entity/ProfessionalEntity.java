package com.cumunity_appointments.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name="professionals")
public class ProfessionalEntity {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String specialty;

    @OneToOne
    @JoinColumn(name="user_id",referencedColumnName="id")
    private UserEntity user;

    @OneToMany(mappedBy="professional")
    private List<AppointmentEntity> appointments;
}
