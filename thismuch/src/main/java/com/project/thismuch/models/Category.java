package com.project.thismuch.models;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Table
@Entity(name = "Category")
@Slf4j
@Getter
@Setter
@RequiredArgsConstructor
public class Category {
    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Integer categoryNo;     // pk
    
    @ManyToOne
    @JoinColumn(name = "user_no", foreignKey = @ForeignKey(name = "user_no_fk"))
    @JsonIgnore
    private User user;         // fk
    
    private String categoryName;    // 카테고리 이름
    
    private Integer upperBound;     // 상한가
}
