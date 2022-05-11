package com.project.thismuch.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Table(name = "account")
@Entity(name = "Account")
@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_no")
    private Integer accountNo;      // pk
    @ManyToOne
    @JoinColumn(name = "user_no", foreignKey = @ForeignKey(name = "user_no_fk"))
    private User userNo;         // fk
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

    public Account(String bankName, String accountNumber, String accountName, String bankCodeStd, String fintechUseNum) {
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.bankCodeStd = bankCodeStd;
        this.fintechUseNum = fintechUseNum;
    }
}
