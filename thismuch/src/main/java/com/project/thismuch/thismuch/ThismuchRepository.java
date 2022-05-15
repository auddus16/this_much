package com.project.thismuch.thismuch;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.thismuch.data.entities.CategoryEntity;
import com.project.thismuch.data.entities.TransitionEntity;
import com.project.thismuch.data.entities.UserEntity;

@Repository
public interface ThismuchRepository extends CrudRepository<TransitionEntity, Long>{
	
	@Query("SELECT tran FROM Transition tran where tran.userNo = :user AND tran.incomeOutcome = 1 AND tran.tranTime BETWEEN :fromDate AND :toDate")
	List<Optional<TransitionEntity>> findSpendingByUserNo(@Param("user")UserEntity user, @Param("fromDate")LocalDate fromDate, @Param("toDate")LocalDate toDate);
	
	@Query("SELECT tran FROM Transition tran where tran.userNo = :user AND tran.incomeOutcome = 2 AND tran.tranTime BETWEEN :fromDate AND :toDate")
	List<Optional<TransitionEntity>> findIncomeByUserNo(@Param("user")UserEntity user, @Param("fromDate")LocalDate fromDate, @Param("toDate")LocalDate toDate);
	
	@Query("SELECT SUM(tran.cost) FROM Transition tran where tran.categoryNo = :category AND tran.userNo = :user AND tran.tranTime BETWEEN :fromDate AND :toDate")
	Optional<String> findTotalCostByCategory(@Param("user")UserEntity user, @Param("category")CategoryEntity category, @Param("fromDate")LocalDate fromDate, @Param("toDate")LocalDate toDate);
	
	@Query("SELECT cate FROM Category cate where cate.userNo = :user")
	List<Optional<CategoryEntity>> findCategoryAll(@Param("user")UserEntity user); // 카테고리 리스트 전체 조회
	
	
}

