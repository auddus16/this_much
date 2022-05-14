package com.project.thismuch.models;

import javax.persistence.Column;
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

@Table(name = "account")
@Entity(name = "Account")
@Slf4j
@Getter
@Setter
@RequiredArgsConstructor
public class Account {
    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_no")
    @JsonIgnore
    private Integer accountNo;      // pk
    
    @ManyToOne
    @JoinColumn(name = "user_no", foreignKey = @ForeignKey(name = "user_no_fk"))
    @JsonIgnore
    private User user;         // fk
    
    @Column(name = "bank_name")
    private String bankName;        // 은행이름
    
    @Column(name = "account_number")
    private String accountNumber;   // 계좌번호
    
    @Column(name = "account_name")
    private String accountName;     // 계좌별칭
    
    @Column(name = "bank_code_std")
    private String bankCodeStd;     // 은행고유번호(국민:004)
    
    @Column(name = "fintech_use_num")
    private String fintechUseNum;  // 핀테크이용번호

}
