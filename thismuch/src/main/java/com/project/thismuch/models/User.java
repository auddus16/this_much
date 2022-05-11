package com.project.thismuch.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class User {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "user_sequence")
    private Integer userNo; // pk

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

    public User(String id, String name, String passwd, String telNum, LocalDate registDate, String code, String regiterToken, String accessToken, String refreshToken, Integer expiration, Integer userSerialNumber) {
        this.id = id;
        this.name = name;
        this.passwd = passwd;
        this.telNum = telNum;
        this.registDate = registDate;
        this.code = code;
        this.registerToken = regiterToken;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiration = expiration;
        this.userSerialNumber = userSerialNumber;
    }
}
