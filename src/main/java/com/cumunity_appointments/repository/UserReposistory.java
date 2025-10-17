package com.cumunity_appointments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cumunity_appointments.entity.UserEntity;


@Repository
public interface UserReposistory extends JpaRepository<UserEntity, Long> {
    
    UserEntity findByEmail(String email);
}
