package com.project.thismuch.oauth;

import com.project.thismuch.data.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthRepository extends CrudRepository<UserEntity, Long> {
}
