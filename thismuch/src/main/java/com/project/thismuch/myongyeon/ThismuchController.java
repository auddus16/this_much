package com.project.thismuch.myongyeon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/thismuch")
class ThismuchController { // 이만큼 탭  + 수입/지출 조회 Controller
	
	@Autowired
	private final ThismuchService thismuchService;
	
	@Operation(summary = "총 지출 내역 조회", description = "원하는 기간의 총 지출 내역을 조회한다.")
	@RequestMapping(value="/spending/{from_date}/{to_date}", method=RequestMethod.GET)
	public ResponseEntity<?> searchSpendingList(@Parameter(name="from_date", description="조회시작일자", example = "YYYYMMDD", in = ParameterIn.PATH)@PathVariable String from_date, 
			@Parameter(name="to_date", description="조회종료일자", example = "YYYYMMDD", in = ParameterIn.PATH)@PathVariable String to_date) {
		log.info("[/spending] 기간 총 지출 내역 조회");
		
		return ResponseEntity.ok("");
	}
	
	@Operation(summary = "총 수입 내역 조회", description = "원하는 기간의 총 수입 내역을 조회한다.")
	@RequestMapping(value="/income/{from_date}/{to_date}", method=RequestMethod.GET)
	public ResponseEntity<?> searchTranAnctNum(@Parameter(name="from_date", description="조회시작일자", example = "YYYYMMDD", in = ParameterIn.PATH)@PathVariable String from_date, 
			@Parameter(name="to_date", description="조회종료일자", example = "YYYYMMDD", in = ParameterIn.PATH)@PathVariable String to_date){
		log.info("[/income] 기간 총 수입 내역 조회");
		
		return ResponseEntity.ok("");
	}
	
	@Operation(summary = "이만큼 정보 조회", description = "원하는 기간의 이만큼 정보를 조회한다.")
	@RequestMapping(value="/stats/{from_date}/{to_date}", method=RequestMethod.GET)
	public ResponseEntity<?> searchTranFinNum(@Parameter(name="from_date", description="조회시작일자", example = "YYYYMMDD", in = ParameterIn.PATH)@PathVariable String from_date, 
			@Parameter(name="to_date", description="조회종료일자", example = "YYYYMMDD", in = ParameterIn.PATH)@PathVariable String to_date){
		log.info("[/stats] 이만큼 정보 조회");
		
		return ResponseEntity.ok("");
	}
	
}
