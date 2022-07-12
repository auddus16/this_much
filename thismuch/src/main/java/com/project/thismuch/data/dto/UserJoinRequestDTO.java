package com.project.thismuch.data.dto;

import com.project.thismuch.data.entities.UserEntity;

import io.swagger.annotations.ApiModelProperty;
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
public class UserJoinRequestDTO {
	
	@ApiModelProperty(example = "example", notes="5자 이상")
    private String userId;
	@ApiModelProperty(example = "예시")
    private String name;
	@ApiModelProperty(example = "example123!", notes="8~25자 영어, 숫자, 특수문자 포함")
    private String passwd;
	@ApiModelProperty(example = "02(010)-123(1234)-1234", notes="-(하이픈) 포함")
    private String telNum;               // 전화 번호
	@ApiModelProperty(example = "example@example.com")
    private String email;
    private String registerToken;
    private String accessToken;
    private String refreshToken;
    private Integer expiration;             // 만료 기간 (초)
    private String userSerialNumber;       // 사용자 일련 번호

    public UserEntity toEntity() {
        return UserEntity.builder()
                .userId(userId)
                .name(name)
                .passwd(passwd)
                .telNum(telNum)
                .email(email)
                .registerToken(registerToken)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiration(expiration)
                .userSerialNumber(userSerialNumber)
                .build();
    }
}
