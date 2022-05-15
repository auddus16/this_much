package com.project.thismuch.thismuch;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

@Service("ThismuchService")
@AllArgsConstructor
public class ThismuchServiceImpl implements ThismuchService{
	
	@Autowired
	ThismuchRepository thismuchRepository;
	
	public LocalDate[] StringToLocalDate(String date1, String date2) {//String 날짜 변환(String->LocalDate)
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		
		LocalDate d1 = LocalDate.parse(date1, formatter);
		LocalDate d2 = LocalDate.parse(date2, formatter);
		
		LocalDate[] res = {d1, d2};
		
		return res;
	}
	
	@Override
	public List<Optional<TransitionEntity>> selectSpendingByPeriod(UserEntity user, String fromDate, String toDate) throws ParseException{ // 총 지출 내역 조회
		
		LocalDate[] date = StringToLocalDate(fromDate, toDate);
		
		return thismuchRepository.findSpendingByUserNo(user, date[0], date[1]);
	
	}
	
	@Override
	public List<Optional<TransitionEntity>> selectIncomeByPeriod(UserEntity user, String fromDate, String toDate) throws ParseException {
		
		LocalDate[] date = StringToLocalDate(fromDate, toDate);
		
		return thismuchRepository.findIncomeByUserNo(user, date[0], date[1]);
	}
	
	@Override
	public Map<String, Optional<String>>getTotalCostByCategory(UserEntity user, String fromDate, String toDate) throws ParseException {
		
		List<Optional<CategoryEntity>> cateList = thismuchRepository.findCategoryAll(user);
		
		Map<String, Optional<String>> resultList = new HashMap<String, Optional<String>>();
		
		LocalDate[] date = StringToLocalDate(fromDate, toDate);

		for(int i=0; i<cateList.size(); i++) {
//			System.out.println(cateList.get(i).get().getCategoryName());
			
			Optional<String> cate = thismuchRepository.findTotalCostByCategory(user, cateList.get(i).get(), date[0], date[1]);
			
			resultList.put(cateList.get(i).get().getCategoryName(), cate);
			
		}
		
		return resultList;
	}
	

}
