package com.project.thismuch.myongyeon;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.thismuch.models.Transition;
import com.project.thismuch.models.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/thismuch")
class ThismuchController { // 이만큼 탭  + 수입/지출 조회 Controller
	
	@Autowired
	private final ThismuchService thismuchService;
	
	@Operation(summary = "총 지출 내역 조회", description = "원하는 기간의 총 지출 내역을 조회한다.")
	@RequestMapping(value="/spending/{fromDate}/{toDate}", method=RequestMethod.GET)
	public ResponseEntity<?> searchSpendingList(@Parameter(name="fromDate", description="조회시작일자", example = "YYYYMMDD", in = ParameterIn.PATH)@PathVariable String fromDate, 
			@Parameter(name="toDate", description="조회종료일자", example = "YYYYMMDD", in = ParameterIn.PATH)@PathVariable String toDate) throws ParseException {
		
		log.info("[/spending] 기간 총 지출 내역 조회");
		
		int userNo = 1; //session 값 받아오는 부분 수정해야함.
		User user1=new User();
		user1.setUserNo(userNo);
		
		List<Optional<Transition>> tranList= thismuchService.selectSpendingByPeriod(user1, fromDate, toDate);
		
		return ResponseEntity.ok(tranList);
	}
	
	@Operation(summary = "총 수입 내역 조회", description = "원하는 기간의 총 수입 내역을 조회한다.")
	@RequestMapping(value="/income/{fromDate}/{toDate}", method=RequestMethod.GET)
	public ResponseEntity<?> searchTranAnctNum(@Parameter(name="fromDate", description="조회시작일자", example = "YYYYMMDD", in = ParameterIn.PATH)@PathVariable String fromDate, 
			@Parameter(name="toDate", description ="조회종료일자", example = "YYYYMMDD", in = ParameterIn.PATH)@PathVariable String toDate) throws ParseException{
		
		log.info("[/income] 기간 총 수입 내역 조회");
		
		int userNo = 1; //session 값 받아오는 부분 수정해야함.
		User user=new User();
		user.setUserNo(userNo);
		
		List<Optional<Transition>> tranList= thismuchService.selectIncomeByPeriod(user, fromDate, toDate);
		
		return ResponseEntity.ok(tranList);
	}
	
	@Operation(summary = "이만큼 정보 조회", description = "원하는 기간의 이만큼 정보를 조회한다.")
	@RequestMapping(value="/stats/{fromDate}/{toDate}", method=RequestMethod.GET)
	public ResponseEntity<?> searchTranFinNum(@Parameter(name="from_date", description="조회시작일자", example = "YYYYMMDD", in = ParameterIn.PATH)@PathVariable String fromDate, 
			@Parameter(name="to_date", description="조회종료일자", example = "YYYYMMDD", in = ParameterIn.PATH)@PathVariable String toDate) throws ParseException{
		log.info("[/stats] 이만큼 정보 조회");

		int userNo = 1; //session 값 받아오는 부분 수정해야함.
		User user=new User();
		user.setUserNo(userNo);
		
		Map<String, Map<String, Optional<String>>> result = new HashMap<String, Map<String, Optional<String>>>();
		
		//카테고리별 총 지출 리스트
		result.put("category_list", thismuchService.getTotalCostByCategory(user, fromDate, toDate));
		
		//고정 지출 리스트
		
		//월 지출 리스트
		
		return ResponseEntity.ok(result);
	}
	
	@Operation(summary = "이만큼 정보 조회", description = "원하는 기간의 이만큼 정보를 조회한다.")
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public ResponseEntity<?> test() throws ParseException{
		log.info("[/test]");
		
		int userNo = 1; //session 값 받아오는 부분 수정해야함.
		User user=new User();
		user.setUserNo(userNo);
//		Object obj = thismuchService.test(user, "20220512", "20220513");
//		
//		System.out.println(obj.getClass().getName().getClass());
		Map<String, Optional<String>> result = thismuchService.getTotalCostByCategory(user, "20220512", "20220513");
		
		return ResponseEntity.ok(result);
	}
	
}
