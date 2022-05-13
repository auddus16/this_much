package com.project.thismuch.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.SEQUENCE;

@Table
@Entity(name = "Transition")
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
    @SequenceGenerator(name = "transition_sequence", sequenceName = "transition_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "transition_sequence")
    private Integer tranNo;

    @ManyToOne
    @JoinColumn(name = "user_no", foreignKey = @ForeignKey(name = "user_no_fk"))
    private User userNo;

    @ManyToOne
    @JoinColumn(name = "account_no", foreignKey = @ForeignKey(name = "account_no"))
    private Account accountNo;

    @ManyToOne
    @JoinColumn(name = "category_no", foreignKey = @ForeignKey(name = "category_no"))
    private Category categoryNo;

    @Column(name = "cost")
    private Integer cost;
    @Column(name = "income_outcome")
    private InOutCome incomeOutcome;
    @Column(name = "content")
    private String content;
    @Column(name = "tran_time")
    private LocalDate tranTime;

    public Transition(Integer cost, InOutCome incomeOutcome, String content, LocalDate tranTime) {
        this.cost = cost;
        this.incomeOutcome = incomeOutcome;
        this.content = content;
        this.tranTime = tranTime;
    }
}
