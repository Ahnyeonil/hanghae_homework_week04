package com.sparta.hanghae_homework_week04.controller;

import com.sparta.hanghae_homework_week04.dto.CommentDto;
import com.sparta.hanghae_homework_week04.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/api/comments")
    public List<CommentDto> getAllPosts() {
        return commentService.findAll();
    }

    @PostMapping("/api/write")
    public void writeComment(@RequestBody CommentDto requestDto) {
        commentService.save(requestDto);
    }

    @PutMapping("/api/comments/{id}")
    public Long updateComment(@RequestBody CommentDto requestDto, @PathVariable long id) {
        return commentService.update(requestDto, id);
    }

    @DeleteMapping("/api/comments/{id}")
    public Long deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);
    }
}
