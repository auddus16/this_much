package com.project.thismuch.data.dto;

import java.time.LocalDate;

import com.project.thismuch.data.entities.AccountEntity;
import com.project.thismuch.data.entities.CategoryEntity;
import com.project.thismuch.data.entities.TransitionEntity;
import com.project.thismuch.data.entities.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TransitionDTO {
    private Long tranNo;
    private UserEntity userNo;
    private AccountEntity accountNo;
    private CategoryEntity categoryNo;
    private Integer cost;
    private String incomeOutcome;
    private String content;
    private LocalDate tranTime;

    public TransitionEntity toEntity() {
        return TransitionEntity.builder()
                .tranNo(tranNo)
                .userNo(userNo)
                .accountNo(accountNo)
                .categoryNo(categoryNo)
                .cost(cost)
                .incomeOutcome(incomeOutcome)
                .content(content)
                .tranTime(tranTime)
                .build();
    }
}
