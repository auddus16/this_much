package com.project.thismuch.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;


@Entity
@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transition {
    enum InOutCome {
        INCOME, OUTCOME
    }

    @Id
    private Integer tranNo;
    private Integer userNo;
    private String accountNo;
    private Integer categoryNo;
    private Integer cost;
    private InOutCome incomeOutcome;
    private String content;
    private LocalDate tranTime;

    public Transition(Integer userNo, String accountNo, Integer categoryNo, Integer cost, InOutCome incomeOutcome, String content, LocalDate tranTime) {
        this.userNo = userNo;
        this.accountNo = accountNo;
        this.categoryNo = categoryNo;
        this.cost = cost;
        this.incomeOutcome = incomeOutcome;
        this.content = content;
        this.tranTime = tranTime;
    }
}
