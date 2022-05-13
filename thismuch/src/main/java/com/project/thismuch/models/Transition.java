package com.project.thismuch.models;

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

@Table
@Entity(name = "Transition")
@Slf4j
@Getter
@Setter
@RequiredArgsConstructor
public class Transition {
    
	enum InOutCome {
        INCOME, OUTCOME
    }

    @Id
    @SequenceGenerator(name = "transition_sequence", sequenceName = "transition_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "transition_sequence")
    @JsonIgnore
    private Integer tranNo;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_no", foreignKey = @ForeignKey(name = "user_no_fk"))
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
    
    
}
