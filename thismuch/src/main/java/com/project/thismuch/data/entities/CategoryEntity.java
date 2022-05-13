package com.project.thismuch.data.entities;

import com.project.thismuch.data.dto.CategoryDTO;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Table
@Entity(name = "Category")
@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer categoryNo;     // pk
    @ManyToOne
    @JoinColumn(name = "user_no", foreignKey = @ForeignKey(name = "user_no_fk"))
    private UserEntity userNo;         // fk
    @Column(name = "category_name")
    private String categoryName;    // 카테고리 이름
    @Column(name = "upper_bound")
    private Integer upperBound;     // 상한가

    public CategoryDTO toDTO() {
        return CategoryDTO.builder()
                .categoryNo(categoryNo)
                .userNo(userNo)
                .categoryName(categoryName)
                .upperBound(upperBound)
                .build();
    }
}
