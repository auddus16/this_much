package com.project.thismuch.mw;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.project.thismuch.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.thismuch.data.dao.UserDAO;
import com.project.thismuch.data.dto.LoginRequestDTO;
import com.project.thismuch.data.dto.UserDTO;
import com.project.thismuch.data.dto.UserJoinRequestDTO;
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
	
	public Long signUp(UserJoinRequestDTO user) {
		UserEntity saveUser = user.toEntity();
		long epoch = System.currentTimeMillis()/1000;
		epoch /= 10;
		String encryptedPassword = encoder.encode(user.getPasswd());
		saveUser.setPasswd(encryptedPassword);
		saveUser.setRegistDate(LocalDate.now());
		saveUser.setBankTranId("M202200570U"+epoch);
		return this.userRepository.saveAndFlush(saveUser).getUserNo();
	}
	
	public boolean validation(UserJoinRequestDTO user) {
		String idRegex = "^[A-Za-z0-9+]{5,}$";
		String pwRegex = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$";
		String emailRegex = "^[0-9a-zA-Z]([-_\\\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$";
		String phoneRegex = "^\\d{2,3}[-]\\d{3,4}[-]\\d{4}$";
		Pattern idP = Pattern.compile(idRegex);
		Pattern pwP = Pattern.compile(pwRegex);
		Pattern emailP = Pattern.compile(emailRegex);
		Pattern phoneP = Pattern.compile(phoneRegex);
		Matcher idM = idP.matcher(user.getUserId());
		Matcher pwM = pwP.matcher(user.getPasswd());
		Matcher emailM = emailP.matcher(user.getEmail());
		Matcher phoneM = phoneP.matcher(user.getTelNum());
		
		if(idM.matches() && pwM.matches() && emailM.matches() && phoneM.matches()) {
			return true;
		}
		else	return false;
	}
	
	public boolean login(LoginRequestDTO map) {
		Optional<UserEntity> user= this.userRepository.findByUserId(map.getUserId());
		return encoder.matches(map.getPasswd(), user.get().getPasswd());
	}
	
	//userNo 조회
	public Long loginSession(String id) {
		Optional<UserEntity> user= this.userRepository.findByUserId(id);
		return user.get().getUserNo();
	}
	
	// my info 조회
	public String myInfo(Object myNo) {
    	log.info("my info 조회 호출");
    	RestTemplate restTemplate = new RestTemplate();
    	Optional<UserEntity> user = this.userRepository.findByUserNo(myNo);
    	
    	
    	String url = "https://testapi.openbanking.or.kr/v2.0/user/me?user_seq_no=";
    	String myToken = user.get().getAccessToken();
    	String myNumber = user.get().getUserSerialNumber();
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
	public String balance(Object myNo) {
		RestTemplate restTemplate = new RestTemplate();
		Optional<UserEntity> user = this.userRepository.findByUserNo(myNo);
		
    	String url = "https://testapi.openbanking.or.kr/v2.0/account/balance/fin_num?";
    	String myToken = user.get().getAccessToken();
    	HttpHeaders headers = new HttpHeaders(); 
    	
    	headers.set("accept", "application/json"); 
    	headers.set("Authorization", "Bearer "+myToken); 
    	
    	HttpEntity<?> request = new HttpEntity<Object>(headers);
    	// parameter
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("bank_tran_id", user.get().getBankTranId());
    	params.put("fintech_use_num", "120220057088941031089438");
    	params.put("tran_dtime", "20220521043010");
    	
    	log.info(request.toString());
    	ResponseEntity<String> response = restTemplate.exchange(url+mapToUrlParam(params), HttpMethod.GET, request, String.class);
    	
    	log.info(url+mapToUrlParam(params));
    	
    	return response.getBody();
	}
	
	// 등록 계좌 조회
	public String myaccount(Object myNo) {
		RestTemplate restTemplate = new RestTemplate();
		Optional<UserEntity> user = this.userRepository.findByUserNo(myNo);
		
		String url = "https://testapi.openbanking.or.kr/v2.0/account/list?";
		String myToken = user.get().getAccessToken();
		String user_seq_num = user.get().getUserSerialNumber();
    	HttpHeaders headers = new HttpHeaders(); 
    	
    	headers.set("accept", "application/json"); 
    	headers.set("Authorization", "Bearer "+myToken); 
    	HttpEntity<?> request = new HttpEntity<Object>(headers);
		// 유저 일련번호(user_seq_num) /user/me 를 통해 조회가능
		Map<String, String> params = new HashMap<String, String>();
    	params.put("user_seq_no", user_seq_num);
    	params.put("include_cancel_yn", "N");
    	params.put("sort_order", "D");
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