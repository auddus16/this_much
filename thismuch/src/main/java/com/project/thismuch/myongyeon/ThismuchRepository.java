package com.project.thismuch.myongyeon;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.thismuch.models.Transition;

@Repository
public interface ThismuchRepository extends CrudRepository<Transition, Long>{

	
}
