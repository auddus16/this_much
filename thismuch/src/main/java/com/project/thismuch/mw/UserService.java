package com.project.thismuch.mw;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.thismuch.data.dao.UserDAO;
import com.project.thismuch.data.dto.UserDTO;
import com.project.thismuch.data.entities.UserEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService implements UserDAO{
	private final UserRepository userRepository;
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	public Long signUp(UserDTO user) {
		String encryptedPassword = encoder.encode(user.getPasswd());
		user.setPasswd(encryptedPassword);
		return this.userRepository.saveAndFlush(user.toEntity()).getUserNo();
	}
	
	public boolean login(HashMap<String, String> map) {
		Optional<UserEntity> user= this.userRepository.findByUserId(map.get("userId"));
		return encoder.matches(map.get("passwd"), user.get().getPasswd());
	}
}