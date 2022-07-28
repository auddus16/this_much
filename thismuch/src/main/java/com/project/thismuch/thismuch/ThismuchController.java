package com.project.thismuch.thismuch;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.thismuch.category.CategoryService;
import com.project.thismuch.data.entities.CategoryEntity;
import com.project.thismuch.data.entities.TransitionEntity;
import com.project.thismuch.data.entities.UserEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/thismuch")
class ThismuchController { // 이만큼 탭  + 캘린더/수입/지출 조회 Controller
	
	@Autowired
	private final ThismuchService thismuchService;
	
	@Autowired
	private final CategoryService categoryService;
	
	@Operation(summary = "지출/수입 통계", description = "조회날짜를 기준으로 이번 달 총 지출/수입액을 조회한다.")
	@RequestMapping(value="/{today}", method=RequestMethod.GET)
	public ResponseEntity<?> searchCostAll(HttpServletRequest request, @Parameter(name="today", description="조회날짜", example = "YYYYMMDD", in = ParameterIn.PATH)@PathVariable String today) throws ParseException {
		
		log.info("[/] 지출/수입 통계");
		
//		HttpSession sess = request.getSession();
		long userNo = 1;
		UserEntity user= new UserEntity();
		user.setUserNo(userNo);
		
		Map<String, Optional<String>> resultList = new HashMap<String, Optional<String>>();
		
		resultList.put("total_spending", thismuchService.getTotalSpendingByMonth(user, today));
		resultList.put("total_income", thismuchService.getTotalIncomeByMonth(user, today));
		
		return ResponseEntity.ok(resultList);
	}
	
	@Operation(summary = "총 거래 내역 조회 (캘린더)", description = "조회날짜를 기준으로 이번 달 거래내역을 조회한다.(캘린더) - 0:수입, 1:지출")
	@RequestMapping(value="/tran/{today}", method=RequestMethod.GET)
	public ResponseEntity<?> searchTranList(HttpServletRequest request, @Parameter(name="today", description="조회날짜", example = "YYYYMMDD", in = ParameterIn.PATH)@PathVariable String today) throws ParseException {
		
		log.info("[/tran] 총 거래내역 조회(캘린더)");
		
//		HttpSession sess = request.getSession();
		long userNo = 1;
		UserEntity user= new UserEntity();
		user.setUserNo(userNo);
		
		List<Optional<Object>> tranList= thismuchService.selectTranAllByPeriod(user, today);
		
		return ResponseEntity.ok(tranList);
	}
	
	@Operation(summary = "총 지출 내역 조회", description = "조회날짜를 기준으로 이번 달 총 지출 내역을 조회한다.")
	@RequestMapping(value="/spending/{today}", method=RequestMethod.GET)
	public ResponseEntity<?> searchSpendingList(HttpServletRequest request, @Parameter(name="today", description="조회날짜", example = "YYYYMMDD", in = ParameterIn.PATH)@PathVariable String today) throws ParseException {
		
		log.info("[/spending] 기간 총 지출 내역 조회");
		
//		HttpSession sess = request.getSession();
		long userNo = 1;
		UserEntity user= new UserEntity();
		user.setUserNo(userNo);
		
		List<Optional<TransitionEntity>> tranList= thismuchService.selectSpendingByPeriod(user, today);
		
		return ResponseEntity.ok(tranList);
	}
	
	@Operation(summary = "총 수입 내역 조회", description = "조회날짜를 기준으로 이번 달 총 수입 내역을 조회한다.")
	@RequestMapping(value="/income/{today}", method=RequestMethod.GET)
	public ResponseEntity<?> searchTranAnctNum(HttpServletRequest request, @Parameter(name="today", description="조회시작일자", example = "YYYYMMDD", in = ParameterIn.PATH)@PathVariable String today) throws ParseException{
		
		log.info("[/income] 기간 총 수입 내역 조회");
		
//		HttpSession sess = request.getSession();
		long userNo = 1;
		UserEntity user= new UserEntity();
		user.setUserNo(userNo);
		
		List<Optional<TransitionEntity>> tranList= thismuchService.selectIncomeByPeriod(user, today);
		
		return ResponseEntity.ok(tranList);
	}
	
	@Operation(summary = "카테고리별 거래 내용 조회", description = "카테고리명 기준으로 거래 내용 조회")
	@RequestMapping(value="/cate/{cateName}", method=RequestMethod.GET)
	public ResponseEntity<?> searchContent(HttpServletRequest request, @Parameter(name="cateName", description="카테고리명", example = "식비", in = ParameterIn.PATH)@PathVariable String cateName) throws ParseException{
		
		log.info("[/cate] 카테고리별 거래 내용 조회");
		
//		HttpSession sess = request.getSession();
		long userNo = 1;
		UserEntity user= new UserEntity();
		user.setUserNo(userNo);
//		System.out.println(user.getUserNo());
		CategoryEntity cate = new CategoryEntity();
		if(cateName.equals("비분류")) {
			
//			cate = new CategoryEntity();
			cate.setCategoryName("비분류");
		}
		else {
			
			cate = categoryService.findCateNoByName(cateName, user);
		}
		
		List<TransitionEntity> contentList= thismuchService.selectContentByCategory(user, cate);
		
		return ResponseEntity.ok(contentList);
	}
	
	@Operation(summary = "이만큼 정보 조회", description = "조회날짜를 기준으로 이번 달의 이만큼 정보를 조회한다.")
	@RequestMapping(value="/stats/{today}", method=RequestMethod.GET)
	public ResponseEntity<?> searchTranFinNum(HttpServletRequest request, @Parameter(name="today", description="조회날짜", example = "YYYYMMDD", in = ParameterIn.PATH)@PathVariable String today) throws ParseException{
		
		log.info("[/stats] 이만큼 정보 조회");

//		HttpSession sess = request.getSession();
		long userNo = 1;
		UserEntity user= new UserEntity();
		user.setUserNo(userNo);
		
		Map<String, Map<String, Optional<String>>> result = new HashMap<String, Map<String, Optional<String>>>();
		
		//카테고리별 총 지출 리스트
		result.put("category_list", thismuchService.getTotalSpendingByCategory(user, today));
		
		//고정 지출 리스트
		result.put("fixed_spending_list", thismuchService.getFixedSpendingList(user, today));
		
		//월 지출 리스트
		result.put("month_list", thismuchService.getTotalSpendingByMonthForFive(user, today));
		
		return ResponseEntity.ok(result);
	}
	
}
