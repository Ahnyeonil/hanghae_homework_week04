package com.sparta.hanghae_homework_week04.service;

import com.sparta.hanghae_homework_week04.domain.Comment;
import com.sparta.hanghae_homework_week04.domain.NoticeBoard;
import com.sparta.hanghae_homework_week04.dto.CommentDto;
import com.sparta.hanghae_homework_week04.dto.NoticeBoardDto;
import com.sparta.hanghae_homework_week04.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    public List<CommentDto> findAll() {

        List<Comment> comments = commentRepository.findAll();
        List<CommentDto> commentDtos = new ArrayList<>();

        for (Comment comment : comments) {

            CommentDto commentDto = new CommentDto(comment);

            commentDtos.add(commentDto);
        }

        return commentDtos;
    }

    public void save(CommentDto requestDto) {

        Comment comment = new Comment(requestDto);

        commentRepository.save(comment);
    }

    public Long update(CommentDto requestDto, long requestId) {

        Comment comment = commentRepository.findById(requestId).orElseThrow(
                () -> new RuntimeException("Comment ID does not exist")
        );

        Comment updateComment = new Comment(requestDto.getComment(), requestDto.getBoardId(), requestDto.getNickname());


        return updateComment.getId();
    }

    public Long deleteComment(Long id) {

        commentRepository.deleteById(id);

        return id;
    }
}
