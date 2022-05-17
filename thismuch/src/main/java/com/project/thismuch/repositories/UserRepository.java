package com.project.thismuch.repositories;

import com.project.thismuch.data.entities.CategoryEntity;
import com.project.thismuch.data.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
