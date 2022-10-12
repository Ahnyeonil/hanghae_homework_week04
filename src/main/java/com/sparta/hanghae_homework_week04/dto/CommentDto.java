package com.sparta.hanghae_homework_week04.dto;

import com.sparta.hanghae_homework_week04.domain.Comment;
import com.sparta.hanghae_homework_week04.domain.NoticeBoard;
import com.sparta.hanghae_homework_week04.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentDto {

    private long id;

    private String comment;

    private NoticeBoard boardId;

    private User nickname;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.boardId = comment.getBoardId();
        this.nickname = comment.getNickname();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
