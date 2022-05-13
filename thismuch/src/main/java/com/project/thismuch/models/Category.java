package com.project.thismuch.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Table
@Entity(name = "Category")
@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer categoryNo;     // pk
    @ManyToOne
    @JoinColumn(name = "user_no", foreignKey = @ForeignKey(name = "user_no_fk"))
    private User userNo;         // fk
    @Column(name = "category_name")
    private String categoryName;    // 카테고리 이름
    @Column(name = "upper_bound")
    private Integer upperBound;     // 상한가
}
