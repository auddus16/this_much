package com.project.thismuch.myongyeon;

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
	public String hi() {
		log.info("Hello world");
		return "hi there~";
	}
	
//	@GetMapping("/auth/openbank/callback")
//    public String getToken(BankRequestToken bankRequestToken, Model model){
//        BankReponseToken token = openBankService.requestToken(bankRequestToken);
//        model.addAttribute("bankReponseToken",token);
//        log.info("bankReponseToken={}", token);
//        return "v1/bank";
//    }
}
