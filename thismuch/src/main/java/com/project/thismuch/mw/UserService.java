package com.project.thismuch.mw;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
	
	// my info 조회
	public String myInfo() {
    	
    	RestTemplate restTemplate = new RestTemplate();
    	
    	String url = "https://testapi.openbanking.or.kr/v2.0/user/me?user_seq_no=";
    	String myToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAxMDA1NTk5Iiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2NjA4NDkzMDksImp0aSI6ImQyMjM2MjI4LWUxODYtNDI0MC1iYzQ4LWUzNzkzOGUxZWM5YiJ9.RFu2-5AKoGF_KTZa0404FtGYeWjL9UFKqxspXYAS9NY";
    	String myNumber = "1101005599";
    	HttpHeaders headers = new HttpHeaders(); 
    	
    	headers.set("accept", "application/json"); 
    	headers.set("Authorization", "Bearer "+myToken); 
    	
    	HttpEntity<?> request = new HttpEntity<Object>(headers);
    	
    	log.info(request.toString());
    	ResponseEntity<String> response = restTemplate.exchange(url+myNumber, HttpMethod.GET, request, String.class);
    	
    	log.info(url+myNumber);
    	
    	return response.getBody();
    	
    }
	
	// 잔액 조회
	public String balance() {
		RestTemplate restTemplate = new RestTemplate();
    	
    	String url = "https://testapi.openbanking.or.kr/v2.0/account/balance/fin_num?";
    	String myToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAxMDA1NTk5Iiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2NjA4NDkzMDksImp0aSI6ImQyMjM2MjI4LWUxODYtNDI0MC1iYzQ4LWUzNzkzOGUxZWM5YiJ9.RFu2-5AKoGF_KTZa0404FtGYeWjL9UFKqxspXYAS9NY";
    	HttpHeaders headers = new HttpHeaders(); 
    	
    	headers.set("accept", "application/json"); 
    	headers.set("Authorization", "Bearer "+myToken); 
    	
    	HttpEntity<?> request = new HttpEntity<Object>(headers);
    	// parameter
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("bank_tran_id", "M202200600U123456789");
    	params.put("fintech_use_num", "120220060088941044080530");
    	params.put("tran_dtime", "20220521043010");
    	
    	log.info(request.toString());
    	ResponseEntity<String> response = restTemplate.exchange(url+mapToUrlParam(params), HttpMethod.GET, request, String.class);
    	
    	log.info(url+mapToUrlParam(params));
    	
    	return response.getBody();
	}
	
	// 명연님이 만드신 parameter mapping
	public String mapToUrlParam(Map<String, String> params) { 
    	
    	StringBuffer paramData = new StringBuffer(); 
    	
    	for (Map.Entry<String, String> param : params.entrySet()) { 
    		if (paramData.length() != 0) { 
    			paramData.append('&'); 
    		} 
    		paramData.append(param.getKey()); 
    		paramData.append('='); 
    		paramData.append(String.valueOf(param.getValue())); 	
    	}
    	return paramData.toString(); 
    }
}