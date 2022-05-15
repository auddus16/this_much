package com.project.thismuch.thismuch;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.thismuch.data.entities.CategoryEntity;
import com.project.thismuch.data.entities.TransitionEntity;
import com.project.thismuch.data.entities.UserEntity;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("ThismuchService")
@AllArgsConstructor
public class ThismuchServiceImpl implements ThismuchService{
	
	@Autowired
	ThismuchRepository thismuchRepository;
	
	//String 날짜 변환(String->LocalDate)
	public LocalDate[] stringToLocalDate(String date1, String date2) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		
		LocalDate d1 = LocalDate.parse(date1, formatter);
		LocalDate d2 = LocalDate.parse(date2, formatter);
		
		LocalDate[] res = {d1, d2};
		
		return res;
	}
	
	//String 날짜 변환(String->LocalDate)
	public LocalDate stringToLocalDate2(String date) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		
		LocalDate d1 = LocalDate.parse(date, formatter);
		
		return d1;
	}
	
	//오늘날짜 기준으로 fromDate, toDate 생성하기.
	public LocalDate[] createDate(String today, int months) {//months는 몇 개월로 생성할 지(1->1개월)
		
		LocalDate toDate = stringToLocalDate2(today);
		int year = toDate.getYear();
		int month = toDate.getMonthValue();
		month -= months-1;//몇 개월로 만들지
		
		String str = Integer.toString(year);
		if(month < 10) { //1~9일 경우 앞에 0 붙여줌.
			str += "0";
		}
		str += Integer.toString(month)+"01";
		log.info(str);
		LocalDate fromDate = stringToLocalDate2(str);
		
		log.info(fromDate+"~"+toDate);
		
		LocalDate[] date = {fromDate, toDate};
		
		return date;
	}
	
	@Override
	public List<Optional<TransitionEntity>> selectSpendingByPeriod(UserEntity user, String fromDate, String toDate) throws ParseException{ // 총 지출 내역 조회
		
		LocalDate[] date = stringToLocalDate(fromDate, toDate);
		
		return thismuchRepository.findSpendingByUserNo(user, date[0], date[1]);
	
	}
	
	@Override
	public List<Optional<TransitionEntity>> selectIncomeByPeriod(UserEntity user, String fromDate, String toDate) throws ParseException {
		
		LocalDate[] date = stringToLocalDate(fromDate, toDate);
		
		return thismuchRepository.findIncomeByUserNo(user, date[0], date[1]);
	}
	
	@Override
	public Map<String, Optional<String>>getTotalCostByCategory(UserEntity user, String today) throws ParseException {
		
		List<Optional<CategoryEntity>> cateList = thismuchRepository.findCategoryAll(user); //category List
		
		//조회날짜로 기간 구하기(20220512 -> 20220501)
		LocalDate[] date = createDate(today, 1);
		
		//비용 계산
		Map<String, Optional<String>> resultList = new HashMap<String, Optional<String>>();		

		for(int i=0; i<cateList.size(); i++) {
			
			Optional<String> cate = thismuchRepository.findTotalCostByCategory(user, cateList.get(i).get(), date[0], date[1]);
			
			resultList.put(cateList.get(i).get().getCategoryName(), cate);
			
		}
		
		return resultList;
	}

	@Override
	public Map<String, Optional<String>> getFixedCostList(UserEntity user, String today)throws ParseException {
		
		//조회날짜로 기간 구하기(20220512 -> 20220401)
		LocalDate[] date = createDate(today, 2);
		
		List<Optional<TransitionEntity>> tranList = thismuchRepository.findSpendingByUserNo(user, date[0], date[1]);
		
		Map<String, Optional<String>> resultList = new HashMap<String, Optional<String>>();
		Map<String, Integer> map = new HashMap<>();
		
		for(Optional<TransitionEntity> t : tranList) {
			
			if(map.containsKey(t.get().getContent())) {// 같은 내용의 거래내역이 존재
				if(map.get(t.get().getContent()).equals(t.get().getCost())) {//금액까지 동일하다면..?
					
					resultList.put(t.get().getContent(), Optional.of(Integer.toString(t.get().getCost())));
				}
			}
			else {
				map.put(t.get().getContent(), t.get().getCost());
			}
			
		}
		
		
		return resultList;
	}
	

}
