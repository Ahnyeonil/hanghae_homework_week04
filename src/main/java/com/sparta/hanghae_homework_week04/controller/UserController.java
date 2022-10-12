package com.sparta.hanghae_homework_week04.controller;

import com.sparta.hanghae_homework_week04.dto.ResponseDto;
import com.sparta.hanghae_homework_week04.dto.UserDto;
import com.sparta.hanghae_homework_week04.dto.UserRequestDto;
import com.sparta.hanghae_homework_week04.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseDto<?> signup(@RequestBody UserDto userDto) throws RuntimeException {
        return userService.signup(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequestDto userRequestDto) throws RuntimeException {
        return userService.login(userRequestDto);
    }
}
