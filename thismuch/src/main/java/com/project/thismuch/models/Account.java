package com.project.thismuch.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer accountNo;      // pk
    private Integer userNo;         // fk
    private String bankName;        // 은행이름
    private String accountNumber;   // 계좌번호
    private String accountName;     // 계좌별칭
    private String bankCodeStd;     // 은행고유번호(국민:004)
    private Integer fintechUseNum;  // 핀테크이용번호

    public Account(Integer userNo, String bankName, String accountNumber, String accountName, String bankCodeStd, Integer fintechUseNum) {
        this.userNo = userNo;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.bankCodeStd = bankCodeStd;
        this.fintechUseNum = fintechUseNum;
    }
}
