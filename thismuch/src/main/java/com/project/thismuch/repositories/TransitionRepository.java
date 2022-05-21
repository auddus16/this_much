package com.project.thismuch.repositories;

import com.project.thismuch.data.dto.TransitionDTO;
import com.project.thismuch.data.entities.TransitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransitionRepository extends JpaRepository<TransitionEntity, Long> {
}
