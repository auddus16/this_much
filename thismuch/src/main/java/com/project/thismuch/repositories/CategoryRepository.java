package com.project.thismuch.repositories;

import com.project.thismuch.data.dto.CategoryDTO;
import com.project.thismuch.data.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> findAllByUserNoUserNo(Long id);
}
