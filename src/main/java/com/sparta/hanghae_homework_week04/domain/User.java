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
@NoArgsConstructor
@Table(name="users")
public class User extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    private String nickname;

    @NotNull
    private String password;

    private Authority authority;

    @Builder
    public User(UserDto userDto) {
        this.nickname = userDto.getNickname();
        this.password = userDto.getPassword();
        this.authority = userDto.getAuthority();
    }

    public User(String nickname, String password, Authority authority) {
        this.nickname = nickname;
        this.password = password;
        this.authority = authority;
    }

}
