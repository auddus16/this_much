package com.project.thismuch.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private Long userNo;                    // pk
    private String id;
    private String name;
    private String passwd;
    private String telNum;                  // 전화 번호
    private LocalDate registDate;
    private String code;
    private String regiterToken;
    private String accessToken;
    private String refreshToken;
    private Integer expiration;             // 만료 기간 (초)
    private Integer userSerialNumber;       // 사용자 일련 번호

    public User(String id, String name, String passwd, String telNum, LocalDate registDate, String code, String regiterToken, String accessToken, String refreshToken, Integer expiration, Integer userSerialNumber) {
        this.id = id;
        this.name = name;
        this.passwd = passwd;
        this.telNum = telNum;
        this.registDate = registDate;
        this.code = code;
        this.regiterToken = regiterToken;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiration = expiration;
        this.userSerialNumber = userSerialNumber;
    }
}
