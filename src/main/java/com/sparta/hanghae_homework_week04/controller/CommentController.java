package com.sparta.hanghae_homework_week04.controller;

import com.sparta.hanghae_homework_week04.dto.CommentRequestDto;
import com.sparta.hanghae_homework_week04.security.UserDetailsImpl;
import com.sparta.hanghae_homework_week04.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    // 실제로는 게시글 조회 시에 같이 조회
    @GetMapping("/comments")
    public List<CommentRequestDto> getAllPosts() {
        return commentService.findAll();
    }

    @PostMapping("/auth/comment/write")
    public void writeComment(@RequestBody CommentRequestDto requestDto,
                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.save(requestDto, userDetails.getUser());
    }

    @PutMapping("/auth/comments/{id}")
    public Long updateComment(@RequestBody CommentRequestDto requestDto,
                              @AuthenticationPrincipal UserDetailsImpl userDetails,
                              @PathVariable long id) {
        return commentService.update(requestDto, userDetails.getUser(), id);
    }

    @DeleteMapping("/auth/comments/{id}")
    public Long deleteComment(@PathVariable Long id,
                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.deleteComment(id, userDetails.getUser());
    }
}
