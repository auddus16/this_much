package com.project.thismuch.data.entities;

import com.project.thismuch.data.dto.TransitionDTO;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

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
@Builder
public class TransitionEntity {
    @Id
    @SequenceGenerator(name = "transition_sequence", sequenceName = "transition_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "transition_sequence")
    private Integer tranNo;

    @ManyToOne
    @JoinColumn(name = "user_no", foreignKey = @ForeignKey(name = "user_no_fk"))
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
}
