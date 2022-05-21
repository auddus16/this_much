package com.project.thismuch.mw;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.thismuch.data.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
    Optional<UserEntity> findByUserId(String userId);
}
