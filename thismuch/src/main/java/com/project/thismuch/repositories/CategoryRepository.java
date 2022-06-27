package com.project.thismuch.repositories;

import com.project.thismuch.data.entities.CategoryEntity;
import com.project.thismuch.data.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> findAllByUserUserNo(Long id);

    //카테고리 리스트 전체 검색
    @Query("SELECT cate FROM Category cate where cate.user = :user")
    List<Optional<CategoryEntity>> findCategoryAll(@Param("user") UserEntity user);
}
