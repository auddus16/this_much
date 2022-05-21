package com.project.thismuch.data.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.thismuch.data.dto.TransitionDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Table
@Entity(name = "Transition")
@Slf4j
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class TransitionEntity {
    
    @Id
    @SequenceGenerator(name = "transition_sequence", sequenceName = "transition_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "transition_sequence")
    @JsonIgnore
    private Integer tranNo;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_no", foreignKey = @ForeignKey())
    private UserEntity userNo;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "account_no", foreignKey = @ForeignKey(name = "account_no"))
    private AccountEntity accountNo;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "category_no", foreignKey = @ForeignKey(name = "category_no"))
    private CategoryEntity categoryNo;

    @Column(name = "cost")
    private Integer cost;
    @Column(name = "income_outcome")
    private String incomeOutcome;
    @Column(name = "content")
    private String content;
    @Column(name = "tran_time")
    private LocalDate tranTime;

    public TransitionDTO toDTO() {
        return TransitionDTO.builder()
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
