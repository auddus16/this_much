package com.project.thismuch.myongyeon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.thismuch.service.RestTemplateService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/my")
public class MyongyeonController {
	
	@Autowired
	private final RestTemplateService templateService;
	
	@GetMapping("/test")
	public ResponseEntity<?> hi() {
		log.info("test연결 완료");
		return ResponseEntity.ok("TEST값");
	}
	
	@GetMapping("/searchTran")
	public ResponseEntity<?> searchTran(){
		log.info("/my/searchTran 호출");
		
		return ResponseEntity.ok(templateService.searchTran());
	}
	
//	@GetMapping("/auth/openbank/callback")
//    public String getToken(BankRequestToken bankRequestToken, Model model){
//        BankReponseToken token = openBankService.requestToken(bankRequestToken);
//        model.addAttribute("bankReponseToken",token);
//        log.info("bankReponseToken={}", token);
//        return "v1/bank";
//    }
}
