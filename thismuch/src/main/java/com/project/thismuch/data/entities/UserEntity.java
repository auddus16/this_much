package com.project.thismuch.data.entities;

import com.project.thismuch.data.dto.UserDTO;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.SEQUENCE;

@Table(name = "user")
@Entity(name = "User")
@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "user_sequence")
    private Long userNo; // pk

    @Column(name = "id", unique = true, nullable = false)
    private String id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "passwd", nullable = false)
    private String passwd;
    @Column(name = "tel_num", nullable = false)
    private String telNum;                  // 전화 번호
    @Column(name = "regist_date", nullable = false)
    private LocalDate registDate;
    @Column(name = "code", nullable = false)
    private String code;
    @Column(name = "register_token", nullable = false)
    private String registerToken;
    @Column(name = "access_token", nullable = false)
    private String accessToken;
    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;
    @Column(name = "expiration", nullable = false)
    private Integer expiration;             // 만료 기간 (초)
    @Column(name = "user_serial_number", nullable = false)
    private Integer userSerialNumber;       // 사용자 일련 번호

    public UserDTO toDTO() {
        return UserDTO.builder()
                .id(id)
                .name(name)
                .passwd(passwd)
                .telNum(telNum)
                .registDate(LocalDate.now())
                .code(code)
                .registerToken(registerToken)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiration(expiration)
                .userSerialNumber(userSerialNumber)
                .build();
    }
}
