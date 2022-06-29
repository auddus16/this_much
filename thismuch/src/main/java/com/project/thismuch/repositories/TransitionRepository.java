package com.project.thismuch.repositories;

import com.project.thismuch.data.dto.TransitionDTO;
import com.project.thismuch.data.entities.CategoryEntity;
import com.project.thismuch.data.entities.TransitionEntity;
import com.project.thismuch.data.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransitionRepository extends JpaRepository<TransitionEntity, Long> {
    //총 거래내역 검색 - 캘린더
    @Query("SELECT tran.tranTime, tran.incomeOutcome, tran.cost FROM Transition tran where tran.userNo = :user AND tran.tranTime BETWEEN :fromDate AND :toDate")
    List<Optional<Object>> findTranAllByUserNo(@Param("user") UserEntity user, @Param("fromDate") LocalDate fromDate, @Param("toDate")LocalDate toDate);

    //총 지출내역 검색
    @Query("SELECT tran FROM Transition tran where tran.userNo = :user AND tran.incomeOutcome = 1 AND tran.tranTime BETWEEN :fromDate AND :toDate")
    List<Optional<TransitionEntity>> findSpendingByUserNo(@Param("user")UserEntity user, @Param("fromDate")LocalDate fromDate, @Param("toDate")LocalDate toDate);

    //총 수입내역 검색
    @Query("SELECT tran FROM Transition tran where tran.userNo = :user AND tran.incomeOutcome = 0 AND tran.tranTime BETWEEN :fromDate AND :toDate")
    List<Optional<TransitionEntity>> findIncomeByUserNo(@Param("user")UserEntity user, @Param("fromDate")LocalDate fromDate, @Param("toDate")LocalDate toDate);

    //카테고리별 총 지출 통계
    @Query("SELECT SUM(tran.cost) FROM Transition tran where tran.categoryNo = :category AND tran.userNo = :user AND tran.tranTime BETWEEN :fromDate AND :toDate ORDER BY SUM(tran.cost) desc")
    Optional<String> findTotalSpendingByCategory(@Param("user")UserEntity user, @Param("category") CategoryEntity category, @Param("fromDate")LocalDate fromDate, @Param("toDate")LocalDate toDate);

    //월별 총 지출액 통계
    @Query("SELECT SUM(tran.cost) FROM Transition tran where tran.userNo = :user AND tran.incomeOutcome = 1 AND tran.tranTime BETWEEN :fromDate AND :toDate")
    Optional<String> findTotalSpendingByMonth(@Param("user")UserEntity user, @Param("fromDate")LocalDate fromDate, @Param("toDate")LocalDate toDate);

    //월별 총 수입액 통계
    @Query("SELECT SUM(tran.cost) FROM Transition tran where tran.userNo = :user AND tran.incomeOutcome = 0 AND tran.tranTime BETWEEN :fromDate AND :toDate")
    Optional<String> findTotalIncomeByMonth(@Param("user")UserEntity user, @Param("fromDate")LocalDate fromDate, @Param("toDate")LocalDate toDate);
    
    //카테고리별 거래내역 검색
    @Query("SELECT tran.content FROM Transition tran where tran.userNo = :user AND tran.categoryNo = :category")
    List<String> findContentAllByUserNo(@Param("user") UserEntity user, @Param("category") CategoryEntity category);

}
