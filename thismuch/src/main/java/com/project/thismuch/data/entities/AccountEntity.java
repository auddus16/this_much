package com.project.thismuch.data.entities;

<<<<<<< HEAD:thismuch/src/main/java/com/project/thismuch/models/Account.java
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
=======
import lombok.*;
>>>>>>> origin/develop:thismuch/src/main/java/com/project/thismuch/data/entities/AccountEntity.java
import lombok.extern.slf4j.Slf4j;

@Table(name = "account")
@Entity(name = "Account")
@Slf4j
@Getter
@Setter
<<<<<<< HEAD:thismuch/src/main/java/com/project/thismuch/models/Account.java
@RequiredArgsConstructor
public class Account {
    
	@Id
=======
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountEntity {
    @Id
>>>>>>> origin/develop:thismuch/src/main/java/com/project/thismuch/data/entities/AccountEntity.java
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_no")
    @JsonIgnore
    private Integer accountNo;      // pk
    
    @ManyToOne
<<<<<<< HEAD:thismuch/src/main/java/com/project/thismuch/models/Account.java
    @JoinColumn(name = "user_no", foreignKey = @ForeignKey(name = "user_no_fk"))
    @JsonIgnore
    private User user;         // fk
    
=======
    @JoinColumn(name = "user_no", foreignKey = @ForeignKey(name = "user_no"))
    private UserEntity userNo;         // fk
>>>>>>> origin/develop:thismuch/src/main/java/com/project/thismuch/data/entities/AccountEntity.java
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

<<<<<<< HEAD:thismuch/src/main/java/com/project/thismuch/models/Account.java
=======
    public AccountEntity(String bankName, String accountNumber, String accountName, String bankCodeStd, Integer fintechUseNum) {
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.bankCodeStd = bankCodeStd;
        this.fintechUseNum = fintechUseNum;
    }
>>>>>>> origin/develop:thismuch/src/main/java/com/project/thismuch/data/entities/AccountEntity.java
}
