package com.project.thismuch.myongyeon;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/my")
class MyongyeonController {
	
	@GetMapping("/test")
<<<<<<< HEAD
	public String hi() {
		log.info("Hello world");
		return "hi there~";
=======
	public ResponseEntity<?> hi() {
		log.info("test연결 완료");
		return ResponseEntity.ok("TEST값");
>>>>>>> 72b5c9df0a046d092498b90b5704c23ec6abe2d0
	}
	
//	@GetMapping("/auth/openbank/callback")
//    public String getToken(BankRequestToken bankRequestToken, Model model){
//        BankReponseToken token = openBankService.requestToken(bankRequestToken);
//        model.addAttribute("bankReponseToken",token);
//        log.info("bankReponseToken={}", token);
//        return "v1/bank";
//    }
}
