package com.sparta.hanghae_homework_week04.domain;

import com.sparta.hanghae_homework_week04.dto.UserDto;
import com.sparta.hanghae_homework_week04.util.Timestamped;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Entity
@Table(name="users")
@NoArgsConstructor
public class User extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z]).{4,12}", message = "닉네임은 최소 4자 이상, 12자 이하 알파벳 대소문자(a-z, A-Z), 숫자(0-9)로 구성됩니다.")
    private String nickname;

    @NotNull
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z]).{4,32}", message = "비밀번호는 최소 4자 이상, 32자 이하 알파벳 소문자(a-z), 숫자(0-9)로 구성됩니다.")
    private String password;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public User(UserDto userDto) {
        this.nickname = userDto.getNickname();
        this.password = userDto.getPassword();
    }
}
