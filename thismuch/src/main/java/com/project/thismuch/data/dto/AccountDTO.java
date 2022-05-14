package com.project.thismuch.data.dto;

import com.project.thismuch.data.entities.AccountEntity;
import com.project.thismuch.data.entities.UserEntity;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AccountDTO {
    private Integer accountNo;      // pk
    private UserEntity userNo;         // fk
    private String bankName;        // 은행이름
    private String accountNumber;   // 계좌번호
    private String accountName;     // 계좌별칭
    private String bankCodeStd;     // 은행고유번호(국민:004)
    private String fintechUseNum;  // 핀테크이용번호

    public AccountEntity toEntity() {
        return AccountEntity.builder()
                .accountNo(accountNo)
                .userNo(userNo)
                .bankName(bankName)
                .accountNumber(accountNumber)
                .accountName(accountName)
                .bankCodeStd(bankCodeStd)
                .fintechUseNum(fintechUseNum)
                .build();
    }
}
