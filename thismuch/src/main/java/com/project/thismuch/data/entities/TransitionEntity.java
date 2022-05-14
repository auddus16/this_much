package com.project.thismuch.data.entities;

<<<<<<< HEAD:thismuch/src/main/java/com/project/thismuch/models/Transition.java
import static javax.persistence.GenerationType.SEQUENCE;

import java.sql.Date;

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

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
=======
import com.project.thismuch.data.dto.TransitionDTO;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.SEQUENCE;
>>>>>>> origin/develop:thismuch/src/main/java/com/project/thismuch/data/entities/TransitionEntity.java

@Table
@Entity(name = "Transition")
@Slf4j
@Getter
@Setter
<<<<<<< HEAD:thismuch/src/main/java/com/project/thismuch/models/Transition.java
@RequiredArgsConstructor
public class Transition {
    
	enum InOutCome {
        INCOME, OUTCOME
    }

=======
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransitionEntity {
>>>>>>> origin/develop:thismuch/src/main/java/com/project/thismuch/data/entities/TransitionEntity.java
    @Id
    @SequenceGenerator(name = "transition_sequence", sequenceName = "transition_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "transition_sequence")
    @JsonIgnore
    private Integer tranNo;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_no", foreignKey = @ForeignKey(name = "user_no_fk"))
<<<<<<< HEAD:thismuch/src/main/java/com/project/thismuch/models/Transition.java
    @JsonIgnore
    private User user;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "account_no", foreignKey = @ForeignKey(name = "account_no_fk"))
    private Account account;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "category_no", foreignKey = @ForeignKey(name = "category_no_fk"))
    private Category category;
    
    private Integer cost;
    
    private InOutCome incomeOutcome;
    
    private String content;
    
    private Date tranTime;
    
    
=======
    private UserEntity userNo;

    @ManyToOne
    @JoinColumn(name = "account_no", foreignKey = @ForeignKey(name = "account_no"))
    private AccountEntity accountNo;

    @ManyToOne
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
>>>>>>> origin/develop:thismuch/src/main/java/com/project/thismuch/data/entities/TransitionEntity.java
}
