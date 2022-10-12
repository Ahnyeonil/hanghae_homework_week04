package com.sparta.hanghae_homework_week04.dto;

import com.sparta.hanghae_homework_week04.domain.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z]).{4,12}", message = "닉네임은 최소 4자 이상, 12자 이하 알파벳 대소문자(a-z, A-Z), 숫자(0-9)로 구성됩니다.")
    private String nickname;

    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z]).{4,32}", message = "비밀번호는 최소 4자 이상, 32자 이하 알파벳 소문자(a-z), 숫자(0-9)로 구성됩니다.")
    private String password;

    private Authority authority;

    private String passwordChk;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    @Builder
    public UserDto(String nickname, String password, Authority authority) {
        this.nickname = nickname;
        this.password = password;
        this.authority = authority;
    }
}
