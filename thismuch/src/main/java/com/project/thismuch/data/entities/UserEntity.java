package com.project.thismuch.data.entities;

<<<<<<< HEAD:thismuch/src/main/java/com/project/thismuch/models/User.java
import static javax.persistence.GenerationType.SEQUENCE;
=======
import com.project.thismuch.data.dto.UserDTO;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
>>>>>>> origin/develop:thismuch/src/main/java/com/project/thismuch/data/entities/UserEntity.java

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Table(name = "user")
@Entity(name = "User")
@Slf4j
@Getter
@Setter
<<<<<<< HEAD:thismuch/src/main/java/com/project/thismuch/models/User.java
@RequiredArgsConstructor
public class User {
    
	@Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "user_sequence")
	@JsonIgnore
    private Integer userNo; // pk
=======
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "user_sequence")
    private Long userNo; // pk
>>>>>>> origin/develop:thismuch/src/main/java/com/project/thismuch/data/entities/UserEntity.java

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
    
    @Column(name = "register_token", nullable = false, length=1000)
    private String registerToken;
    
    @Column(name = "access_token", nullable = false, length=1000)
    private String accessToken;
    
    @Column(name = "refresh_token", nullable = false, length=1000)
    private String refreshToken;
    
    @Column(name = "expiration", nullable = false)
    private Integer expiration;             // 만료 기간 (초)
    
    @Column(name = "user_serial_number", nullable = false)
<<<<<<< HEAD:thismuch/src/main/java/com/project/thismuch/models/User.java
    private String userSerialNumber;       // 사용자 일련 번호
    
=======
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
>>>>>>> origin/develop:thismuch/src/main/java/com/project/thismuch/data/entities/UserEntity.java
}
