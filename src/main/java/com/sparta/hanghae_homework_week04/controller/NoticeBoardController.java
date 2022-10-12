package com.sparta.hanghae_homework_week04.controller;

import com.sparta.hanghae_homework_week04.dto.NoticeBoardDto;
import com.sparta.hanghae_homework_week04.service.NoticeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NoticeBoardController {

    private final NoticeBoardService noticeBoardService;

    @GetMapping("/api/boards")
    public List<NoticeBoardDto> getAllPosts() {
        return noticeBoardService.findAll();
    }

    @PostMapping("/api/write")
    public void writePost(@RequestBody NoticeBoardDto requestDto) {
        noticeBoardService.save(requestDto);
    }

    @GetMapping("/api/boards/{id}")
    public NoticeBoardDto getPost(@PathVariable Long id) {
        return noticeBoardService.getPost(id);
    }

    @PutMapping("/api/boards/{id}")
    public Long updatePost(@RequestBody NoticeBoardDto requestDto, @PathVariable long id) {
        return noticeBoardService.update(requestDto, id);
    }

    @DeleteMapping("/api/boards/{id}")
    public Long deletePost(@PathVariable Long id) {
        return noticeBoardService.deletePost(id);
    }
}
