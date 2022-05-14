package com.project.thismuch.data.dto;

import com.project.thismuch.data.entities.AccountEntity;
import com.project.thismuch.data.entities.CategoryEntity;
import com.project.thismuch.data.entities.TransitionEntity;
import com.project.thismuch.data.entities.UserEntity;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TransitionDTO {
    private Integer tranNo;
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
