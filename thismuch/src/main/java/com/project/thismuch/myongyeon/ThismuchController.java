package com.project.thismuch.myongyeon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/thismuch")
class ThismuchController { // 이만큼 탭  + 수입/지출 조회 Controller
	
	@Autowired
	private final ThismuchService thismuchService;
	
	@ApiOperation(value = "총 수입 내역 조회", notes = "원하는 기간의 총 수입 내역을 조회한다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name="from_date", value="조회시작일자", example = "YYYYMMDD"),
		@ApiImplicitParam(name="to_date", value="조회종료일자", example = "YYYYMMDD")		
	})
	@RequestMapping(value="/spending/{c_no}", method=RequestMethod.GET)
	public ResponseEntity<?> searchSpendingList(@PathVariable String from_date, @PathVariable String to_date) {
		log.info("[/spending] 기간 총 지출 내역 조회");
		
		return ResponseEntity.ok("");
	}
	
	@GetMapping("/income")
	public ResponseEntity<?> searchTranAnctNum(){
		log.info("[/income] 기간 총 수입 내역 조회");
		
		return ResponseEntity.ok("");
	}
	
	@GetMapping("/stats")
	public ResponseEntity<?> searchTranFinNum(){
		log.info("[/stats] 이만큼 정보 조회");
		
		return ResponseEntity.ok("");
	}
	
	
//	@GetMapping("/auth/openbank/callback")
//    public String getToken(BankRequestToken bankRequestToken, Model model){
//        BankReponseToken token = openBankService.requestToken(bankRequestToken);
//        model.addAttribute("bankReponseToken",token);
//        log.info("bankReponseToken={}", token);
//        return "v1/bank";
//    }
}
