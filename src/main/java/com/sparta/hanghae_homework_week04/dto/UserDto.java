package com.sparta.hanghae_homework_week04.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String nickname;

    private String password;

    private String passwordChk;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    @Builder
    public UserDto(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }
}
