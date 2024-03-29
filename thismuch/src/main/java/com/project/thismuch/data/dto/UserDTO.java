package com.project.thismuch.data.dto;

import java.time.LocalDate;

import com.project.thismuch.data.entities.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDTO {

    private Long userNo; // pk
    private String userId;
    private String name;
    private String passwd;
    private String telNum;                  // 전화 번호
    private String email;
    private LocalDate registDate;
    private String registerToken;
    private String accessToken;
    private String refreshToken;
    private Integer expiration;             // 만료 기간 (초)
    private String userSerialNumber;       // 사용자 일련 번호
    private String bankTranId;				//은행거래고유번호

    public UserEntity toEntity() {
        return UserEntity.builder()
                .userId(userId)
                .name(name)
                .passwd(passwd)
                .telNum(telNum)
                .email(email)
                .registDate(LocalDate.now())
                .registerToken(registerToken)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiration(expiration)
                .userSerialNumber(userSerialNumber)
                .bankTranId(bankTranId)
                .build();
    }
}
