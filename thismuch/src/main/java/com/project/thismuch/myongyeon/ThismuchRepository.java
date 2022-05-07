package com.project.thismuch.myongyeon;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.thismuch.models.Account;

@Repository
public interface ThismuchRepository extends CrudRepository<Account, Long>, JpaSpecificationExecutor<Account>{

	
}
