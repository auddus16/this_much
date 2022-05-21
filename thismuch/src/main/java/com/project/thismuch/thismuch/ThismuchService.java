package com.project.thismuch.thismuch;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.project.thismuch.data.entities.TransitionEntity;
import com.project.thismuch.data.entities.UserEntity;

public interface ThismuchService {
	
	//총 지출내역 조회
	List<Optional<TransitionEntity>> selectSpendingByPeriod(UserEntity user, String today)throws ParseException;
	
	//총 수입내역 조회
	List<Optional<TransitionEntity>> selectIncomeByPeriod(UserEntity user, String today)throws ParseException;
	
	//카테고리별 총 지출비용 조회
	Map<String, Optional<String>> getTotalSpendingByCategory(UserEntity user, String today)throws ParseException;
	
	//월별 총 지출비용 조회 - 5개월 간 
	Map<String, Optional<String>> getTotalSpendingByMonthForFive(UserEntity user, String today) throws ParseException;
	
	//고정지출 조회
	Map<String, Optional<String>> getFixedSpendingList(UserEntity user, String today)throws ParseException;
	
	//총 거래내역(지출, 수입) 조회(캘린더)
	List<Optional<Object>> selectTranAllByPeriod(UserEntity user, String today)throws ParseException;
	
	//월별 총 수입비용 조회
	Optional<String> getTotalIncomeByMonth(UserEntity user, String today)throws ParseException;
	
	//월별 총 지줄비용 조회
	Optional<String> getTotalSpendingByMonth(UserEntity user, String today)throws ParseException;
}
