package com.sparta.hanghae_homework_week04.service;

import com.sparta.hanghae_homework_week04.domain.Comment;
import com.sparta.hanghae_homework_week04.domain.User;
import com.sparta.hanghae_homework_week04.dto.CommentRequestDto;
import com.sparta.hanghae_homework_week04.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    public List<CommentRequestDto> findAll() {

        List<Comment> comments = commentRepository.findAll();
        List<CommentRequestDto> commentRequestDtos = new ArrayList<>();

        for (Comment comment : comments) {

            CommentRequestDto commentRequestDto = new CommentRequestDto(comment);

            commentRequestDtos.add(commentRequestDto);
        }

        return commentRequestDtos;
    }

    public void save(CommentRequestDto requestDto, User user) {

        Comment comment = Comment.builder()
                .comment(requestDto.getComment())
                .user(user)
                .noticeBoard(requestDto.getBoardId())
                .build();

        commentRepository.save(comment);
    }

    public Long update(CommentRequestDto requestDto, User user, long requestId) {

        Comment comment = commentRepository.findById(requestId).orElseThrow(
                () -> new RuntimeException("Comment ID does not exist")
        );

        if(!user.getId().equals(comment.getUser().getId())){
            throw new RuntimeException("댓글 작성자가 Login User와 일치하지 않습니다.");
        }

        Comment updateComment = Comment.builder()
                .comment(requestDto.getComment())
                .build();

        return updateComment.getId();
    }

    public Long deleteComment(Long id, User user) {

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Comment ID does not exist")
        );

        if(!user.getId().equals(comment.getUser().getId())){
            throw new RuntimeException("댓글 작성자가 Login User와 일치하지 않습니다.");
        }

        commentRepository.deleteById(id);

        return id;
    }
}
