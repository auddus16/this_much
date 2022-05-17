package com.project.thismuch.thismuch;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.project.thismuch.data.entities.TransitionEntity;
import com.project.thismuch.data.entities.UserEntity;

public interface ThismuchService {
	
	//fromDate~toDate까지 총 지출내역 조회
	List<Optional<TransitionEntity>> selectSpendingByPeriod(UserEntity user, String fromDate, String toDate)throws ParseException;
	
	//fromDate~toDate까지 총 수입내역 조회
	List<Optional<TransitionEntity>> selectIncomeByPeriod(UserEntity user, String fromDate, String toDate)throws ParseException;
	
	//fromDate~toDate까지 카테고리별 총 지출비용 조회
	Map<String, Optional<String>> getTotalCostByCategory(UserEntity user, String today)throws ParseException;
	
	//fromDate~toDate까지 월별 총 지출비용 조회
	
	//fromDate~toDate까지 고정지출 조회
	Map<String, Optional<String>> getFixedCostList(UserEntity user, String today)throws ParseException;
}
