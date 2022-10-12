package com.sparta.hanghae_homework_week04.controller;

import com.sparta.hanghae_homework_week04.dto.NoticeBoardRequestDto;
import com.sparta.hanghae_homework_week04.security.UserDetailsImpl;
import com.sparta.hanghae_homework_week04.service.NoticeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NoticeBoardController {

    private final NoticeBoardService noticeBoardService;

    @GetMapping("/boards")
    public List<NoticeBoardRequestDto> getAllPosts() {
        return noticeBoardService.findAll();
    }

    @PostMapping("/auth/noticeboard/write")
    public void writePost(@RequestBody NoticeBoardRequestDto requestDto,
                          @AuthenticationPrincipal UserDetailsImpl userDetails) {
        noticeBoardService.save(requestDto, userDetails.getUser());
    }

    @GetMapping("/boards/{id}")
    public NoticeBoardRequestDto getPost(@PathVariable Long id) {
        return noticeBoardService.getPost(id);
    }

    @PutMapping("/auth/boards/{id}")
    public Long updatePost(@RequestBody NoticeBoardRequestDto requestDto,
                           @PathVariable long id,
                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return noticeBoardService.update(requestDto, id, userDetails.getUser());
    }

    @DeleteMapping("/auth/boards/{id}")
    public Long deletePost(@PathVariable Long id,
                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return noticeBoardService.deletePost(id, userDetails.getUser());
    }
}
