package com.sparta.hanghae_homework_week04.service;

import com.sparta.hanghae_homework_week04.domain.User;
import com.sparta.hanghae_homework_week04.dto.UserDto;
import com.sparta.hanghae_homework_week04.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto signup(UserDto userDto) throws IOException {

        if(userRepository.existsByNickname(userDto.getNickname())){
            throw new RuntimeException("중복된 닉네임이 존재합니다.");
        }

        if(!userDto.getPassword().equals(userDto.getPasswordChk())){
            throw new RuntimeException("비밀번호 값이 서로 일치하지 않습니다.");
        }

        User saveUser = new User(userDto);

        User user = userRepository.save(saveUser);

        UserDto responseUser = new UserDto(user.getNickname(), user.getPassword());

        return responseUser;
    }

    public UserDto login(UserDto userDto) throws IOException {

        if(!userRepository.existsByNickname(userDto.getNickname())){
            throw new RuntimeException("입력한 아이디가 존재하지 않습니다.");
        }

        User loginUser = userRepository.findByNickname(userDto.getNickname());

        if (!userDto.getPassword().equals(loginUser.getPassword())) {
            throw new RuntimeException("입력한 정보가 일치하지 않습니다.");
        }

        UserDto resultDto = new UserDto(loginUser.getNickname(), loginUser.getPassword());

        return resultDto;

    }

    @Transactional
    public UserDto loadUserByNickname(String nickname) throws UsernameNotFoundException {
        User user = userRepository.findByNickname(nickname);

        UserDto userDto = new UserDto(user.getNickname(), user.getPassword());

        return userDto;
    }
}
