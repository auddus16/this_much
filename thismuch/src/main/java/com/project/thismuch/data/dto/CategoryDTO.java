package com.project.thismuch.data.dto;

import com.project.thismuch.data.entities.CategoryEntity;
import com.project.thismuch.data.entities.UserEntity;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CategoryDTO {
    private Integer categoryNo;     // pk
    private UserEntity userNo;         // fk
    private String categoryName;    // 카테고리 이름
    private Integer upperBound;     // 상한가

    public CategoryEntity toEntity() {
        return CategoryEntity.builder()
                .categoryNo(categoryNo)
                .userNo(userNo)
                .categoryName(categoryName)
                .upperBound(upperBound)
                .build();
    }
}
