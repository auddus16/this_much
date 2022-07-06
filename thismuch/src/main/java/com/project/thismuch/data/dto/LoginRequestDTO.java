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
public class LoginRequestDTO {
	private String userId;
    private String passwd;       // 사용자 일련 번호

    public UserEntity toEntity() {
        return UserEntity.builder()
                .userId(userId)
                .passwd(passwd)
                .build();
    }
}
