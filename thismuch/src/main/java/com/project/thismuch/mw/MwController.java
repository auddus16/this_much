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

import com.project.thismuch.data.dto.LoginRequestDTO;
import com.project.thismuch.data.dto.UserDTO;
import com.project.thismuch.data.dto.UserJoinRequestDTO;

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
	public ResponseEntity<?> signUp(@RequestBody UserJoinRequestDTO user, HttpServletRequest request){
		if(userService.validation(user)) {
			Long no = userService.signUp(user);
			log.info(no.toString());
			return ResponseEntity.ok(null);
		}
		else	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	// login
    @Operation(summary = "로그인 기능", description = "사용자의 id와 password를 통해 로그인 합니다")
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequestDTO login, HttpServletRequest request){
    	HttpSession session = request.getSession();
		log.info("/login 호출");
		if(userService.login(login)) {	//로그인 성공
			session.setAttribute("loginId", userService.loginSession(login.getUserId())); // 로그인 된 현재 id no로 session 값 부여
			log.info(session.getAttribute("loginId").toString());
			return ResponseEntity.ok(null);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	}
    
    // my info
    @Operation(summary = "내 정보 조회", description = "토큰과 일련번호를 사용해 내 개인 정보를 조회합니다")
    @GetMapping("/myinfo")
	public ResponseEntity<?> searchMyInfo(HttpServletRequest request){
		log.info("/myinfo 호출");
		HttpSession session = request.getSession();
		Object myNo = session.getAttribute("loginId");
		return ResponseEntity.ok(userService.myInfo(myNo));
	}
    
    //balance
    @Operation(summary = "잔액 조회", description = "핀테크 이용 번호를 통해 내 남은 잔액을 조회합니다")
    @GetMapping("/balance")
	public ResponseEntity<?> searchBalance(HttpServletRequest request){
		log.info("/balance 호출");
		HttpSession session = request.getSession();
		Object myNo = session.getAttribute("loginId");
		return ResponseEntity.ok(userService.balance(myNo));
	}
    
    // account/list
    @Operation(summary = "내 계좌 조회", description = "토큰과 일련번호를 이용해 내 계좌목록을 조회합니다.")
    @GetMapping("/myaccount/list")
	public ResponseEntity<?> searchAccount(HttpServletRequest request){
		log.info("/myaccount/list 호출");
		HttpSession session = request.getSession();
		Object myNo = session.getAttribute("loginId");
		return ResponseEntity.ok(userService.myaccount(myNo));
	}
}
