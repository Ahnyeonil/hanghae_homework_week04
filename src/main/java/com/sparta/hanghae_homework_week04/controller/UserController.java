package com.sparta.hanghae_homework_week04.controller;

import com.sparta.hanghae_homework_week04.dto.UserDto;
import com.sparta.hanghae_homework_week04.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/signup")
    public UserDto signup(@RequestBody UserDto userDto) throws IOException {
        UserDto resultDto = userService.signup(userDto);
        return resultDto;
    }

    @PostMapping("/user/login")
    public UserDto login(@RequestBody UserDto userDto, Model model) throws IOException {
        UserDto resultDto = userService.login(userDto);
        model.addAttribute("userDto", resultDto);
        return resultDto;
    }
}
