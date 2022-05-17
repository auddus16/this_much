package com.project.thismuch.repositories;

import com.project.thismuch.data.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> findAllByUserNoUserNo(Long user_no);
}
