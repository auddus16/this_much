package com.project.thismuch.myongyeon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThismuchService{
	
	@Autowired
	ThismuchRepository thismuchRepository;
	
//	List<Account> selectSpendingByPeriod(String from, String to); // 총 지출 내역 조회
//	
//	List<Account> selectIncomeByPeriod(String from, String to);	// 총 수입 내역 조회
//	
//	List<?> selectCategoryCostByPeriod(String from, String to); // 카테고리별 지출 내역 조회

}
