package com.cumunity_appointments.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cumunity_appointments.entity.UserEntity;


@Repository
public interface UserReposistory extends JpaRepository<UserEntity, Long> {
    
    Optional<UserEntity> findByEmail(String email);
}
