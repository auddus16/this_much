package com.project.thismuch.thismuch;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.thismuch.data.entities.CategoryEntity;
import com.project.thismuch.data.entities.TransitionEntity;
import com.project.thismuch.data.entities.UserEntity;
import com.project.thismuch.repositories.CategoryRepository;
import com.project.thismuch.repositories.TransitionRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("ThismuchService")
@AllArgsConstructor
public class ThismuchServiceImpl implements ThismuchService{
	
	@Autowired
	TransitionRepository transitionRepository;
	@Autowired
	CategoryRepository categoryRepository;
	
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
	public LocalDate[] createDate(LocalDate today, int months) {//months는 몇 개월로 생성할 지(1->1개월)
		
		LocalDate toDate = today;
		LocalDate fromDate = toDate.minusMonths(months);
		
		fromDate = fromDate.minusDays(fromDate.getDayOfMonth()-1);
		toDate = toDate.with(TemporalAdjusters.lastDayOfMonth());
		
//		log.info(fromDate+"~"+toDate);
		
		LocalDate[] date = {fromDate, toDate};
		
		return date;
	}
	
	@Override
	public List<Optional<TransitionEntity>> selectSpendingByPeriod(UserEntity user, String today) throws ParseException{ // 총 지출 내역 조회
		
		LocalDate day = stringToLocalDate2(today);
		LocalDate[] date = createDate(day, 0);
		
		return transitionRepository.findSpendingByUserNo(user, date[0], date[1]);
	
	}
	
	@Override
	public List<Optional<TransitionEntity>> selectIncomeByPeriod(UserEntity user, String today) throws ParseException {
		
		LocalDate day = stringToLocalDate2(today);
		LocalDate[] date = createDate(day, 0);
		
		return transitionRepository.findIncomeByUserNo(user, date[0], date[1]);
	}
	
	@Override
	public Map<String, Optional<String>>getTotalSpendingByCategory(UserEntity user, String today) throws ParseException {
		
		List<Optional<CategoryEntity>> cateList = categoryRepository.findCategoryAll(user); //category List
		
		//조회날짜로 기간 구하기(20220512 -> 20220501)
		LocalDate[] date = createDate(stringToLocalDate2(today), 0);
		log.info("카테고리 총 지출 조회기간: "+date[0]+"~"+date[1]);
		
		//비용 계산
		Map<String, Optional<String>> resultList = new HashMap<String, Optional<String>>();		

		for(int i=0; i<cateList.size(); i++) {
			
			Optional<String> cate = transitionRepository.findTotalSpendingByCategory(user, cateList.get(i).get(), date[0], date[1]);
			
			resultList.put(cateList.get(i).get().getCategoryName(), cate);
			
		}
		
		return resultList;
	}

	@Override
	public Map<String, Optional<String>> getFixedSpendingList(UserEntity user, String today)throws ParseException {
		
		//조회날짜로 기간 구하기(20220512 -> 20220401~20220531)
		LocalDate[] date = createDate(stringToLocalDate2(today), 4);
		log.info("고정지출 조회기간: "+date[0]+"~"+date[1]);
		
		List<Optional<TransitionEntity>> tranList = transitionRepository.findSpendingByUserNo(user, date[0], date[1]);
		
		Map<String, Optional<String>> resultList = new HashMap<String, Optional<String>>();
		Map<String, Integer> map = new HashMap<>();
		Map<String, Integer> count = new HashMap<>();
		
		for(Optional<TransitionEntity> t : tranList) {
			
			if(map.containsKey(t.get().getContent())) {// 같은 내용의 거래내역이 존재
				if(map.get(t.get().getContent()).equals(t.get().getCost())) {//금액까지 동일하다면..?
					count.put(t.get().getContent(), count.get(t.get().getContent())+1);
					System.out.println(count.get(t.get().getContent()));
				}
			}
			else {
				log.info(t.get().getContent());
				map.put(t.get().getContent(), t.get().getCost());
				count.put(t.get().getContent(), 1);
			}
			
		}
		
		Iterator<String> keys = count.keySet().iterator();
		while(keys.hasNext()) {
			String key = keys.next();
			if(count.get(key) >= 2) { //2번 이상
				resultList.put(key, Optional.of(Integer.toString(map.get(key))));
				log.info(key+count.get(key));
			}
			
		}
//		for(Optional<TransitionEntity> t : tranList) {
//			
//			if(count.get(t.get().getContent()) >= 2) { //2번 이상
//				resultList.put(t.get().getContent(), Optional.of(Integer.toString(t.get().getCost())));
//			}
//		}
		
		return resultList;
	}
	
	@Override
	public Map<String, Optional<String>> getTotalSpendingByMonthForFive(UserEntity user, String today)throws ParseException {
		
		//비용 계산
		Map<String, Optional<String>> resultList = new HashMap<String, Optional<String>>();		
		
		LocalDate day = stringToLocalDate2(today);
		LocalDate[] date = new LocalDate[2];
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
		for(int i=0; i<5; i++) {
			
			date = createDate(day, 0);
			
			Optional<String> totalCost = transitionRepository.findTotalSpendingByMonth(user, date[0], date[1]);
			
			resultList.put(date[0].format(formatter), totalCost);
			
			day = day.minusMonths(1);
			
		}
		
		return resultList;
	}

	@Override
	public List<Optional<Object>> selectTranAllByPeriod(UserEntity user, String today) throws ParseException {
		
		LocalDate[] date = createDate(stringToLocalDate2(today), 0);
		
//		System.out.println(thismuchRepository.findTranAllByUserNo(user, date[0], date[1]));
		
		return transitionRepository.findTranAllByUserNo(user, date[0], date[1]);
	}

	@Override
	public Optional<String> getTotalIncomeByMonth(UserEntity user, String today) throws ParseException {

		LocalDate day = stringToLocalDate2(today);
		LocalDate[] date = createDate(day, 0);
		
		Optional<String> resultList = transitionRepository.findTotalIncomeByMonth(user, date[0], date[1]);
	
		return resultList;		
	}

	@Override
	public Optional<String> getTotalSpendingByMonth(UserEntity user, String today) throws ParseException {
		
		LocalDate day = stringToLocalDate2(today);
		LocalDate[] date = createDate(day, 0);
		
		Optional<String> resultList = transitionRepository.findTotalSpendingByMonth(user, date[0], date[1]);
	
		return resultList;
	}

	@Override
	public List<String> selectContentByCategory(UserEntity user, CategoryEntity category) throws ParseException {
		
		
		return transitionRepository.findContentAllByUserNo(user, category);
	}
	
}
