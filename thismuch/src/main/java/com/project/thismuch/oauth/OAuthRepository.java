package com.project.thismuch.oauth;

import com.project.thismuch.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthRepository extends CrudRepository<User, Long> {
}
