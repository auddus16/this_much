package com.project.thismuch.data.dto;

import com.project.thismuch.data.entities.UserEntity;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import java.time.LocalDate;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDTO {
    private Integer userNo; // pk
    private String id;
    private String name;
    private String passwd;
    private String telNum;                  // 전화 번호
    private LocalDate registDate;
    private String code;
    private String registerToken;
    private String accessToken;
    private String refreshToken;
    private Integer expiration;             // 만료 기간 (초)
    private Integer userSerialNumber;       // 사용자 일련 번호

    public UserEntity toEntity() {
        return UserEntity.builder()
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
