package com.project.thismuch.myongyeon;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.thismuch.models.Category;
import com.project.thismuch.models.Transition;
import com.project.thismuch.models.User;

@Repository
public interface ThismuchRepository extends CrudRepository<Transition, Long>{
	
	@Query("SELECT tran FROM Transition tran where tran.user = :user AND tran.incomeOutcome = 1 AND tran.tranTime BETWEEN :fromDate AND :toDate")
	List<Optional<Transition>> findSpendingByUserNo(@Param("user")User user, @Param("fromDate")Date fromDate, @Param("toDate")Date toDate);
	
	@Query("SELECT tran FROM Transition tran where tran.user = :user AND tran.incomeOutcome = 2 AND tran.tranTime BETWEEN :fromDate AND :toDate")
	List<Optional<Transition>> findIncomeByUserNo(@Param("user")User user, @Param("fromDate")Date fromDate, @Param("toDate")Date toDate);
	
	@Query("SELECT SUM(tran.cost) FROM Transition tran where tran.category = :category AND tran.user = :user AND tran.tranTime BETWEEN :fromDate AND :toDate")
	Optional<String> findTotalCostByCategory(@Param("user")User user, @Param("category")Category category, @Param("fromDate")Date fromDate, @Param("toDate")Date toDate);
	
	@Query("SELECT cate FROM Category cate where cate.user = :user")
	List<Optional<Category>> findCategoryAll(@Param("user")User user); // 카테고리 전체 조회
	
	
}

