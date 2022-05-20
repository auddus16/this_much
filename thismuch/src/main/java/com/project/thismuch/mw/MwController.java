package com.project.thismuch.mw;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.thismuch.data.dto.UserDTO;

import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ExampleProperty;
import io.swagger.annotations.Example;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/mw")
public class MwController {
	@Autowired
	UserService userService;
	
	// sing up + 추후 중복 아이디 및 형식에 따른 검증 구현 필요함 + Exception 추후 구현
	@Operation(summary = "회원가입", description = "회원가입 기능 입니다")
	@PostMapping("/signUp")
	public ResponseEntity<?> signUp(@RequestBody UserDTO user, HttpServletRequest request){
		Long no = userService.signUp(user);
		log.info(no.toString());
		
		return ResponseEntity.ok(null);
	}
	
	// login
    @Operation(summary = "로그인 기능", description = "사용자의 id와 password를 통해 로그인 합니다")
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody HashMap<String, String> login, HttpServletRequest request){
    	HttpSession session = request.getSession();
		log.info("/login 호출");
		if(userService.login(login)) {	//로그인 성공
			session.setAttribute("loginId", login.get("userId")); // 로그인 된 현재 id로 session 값 부여
			return ResponseEntity.ok(null);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	}
}
