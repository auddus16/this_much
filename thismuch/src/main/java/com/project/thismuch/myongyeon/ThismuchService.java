package com.project.thismuch.myongyeon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.thismuch.models.Category;
import com.project.thismuch.models.Transition;
import com.project.thismuch.models.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ThismuchService{
	
	@Autowired
	ThismuchRepository thismuchRepository;
	
	private final SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	
	List<Optional<Transition>> selectSpendingByPeriod(User user, String fromDate, String toDate) throws ParseException{ // 총 지출 내역 조회
		
		Date from = formatter.parse(fromDate);
		Date to = formatter.parse(toDate);
		
		return thismuchRepository.findSpendingByUserNo(user, from, to);
	
	}

	public List<Optional<Transition>> selectIncomeByPeriod(User user, String fromDate, String toDate) throws ParseException {
		
		Date from = formatter.parse(fromDate);
		Date to = formatter.parse(toDate);
		
		return thismuchRepository.findIncomeByUserNo(user, from, to);
	}
	
	public Map<String, Optional<String>>getTotalCostByCategory(User user, String fromDate, String toDate) throws ParseException {
		
		List<Optional<Category>> cateList = thismuchRepository.findCategoryAll(user);
		
		Map<String, Optional<String>> resultList = new HashMap<String, Optional<String>>();
		
		Date from = formatter.parse(fromDate);
		Date to = formatter.parse(toDate);

		for(int i=0; i<cateList.size(); i++) {
			System.out.println(cateList.get(i).get().getCategoryName());
			
			Optional<String> cate = thismuchRepository.findTotalCostByCategory(user, cateList.get(i).get(), from, to);
			
//			Map<String, Optional<String>> resultList = new HashMap<String, Optional<String>>();
			resultList.put(cateList.get(i).get().getCategoryName(), cate);
			
		}
		
		return resultList;
	}
	
//	
//	List<Transition> selectIncomeByPeriod(String from, String to);	// 총 수입 내역 조회
//	
//	List<?> selectCategoryCostByPeriod(String from, String to); // 카테고리별 지출 내역 조회

}
