package com.sparta.hanghae_homework_week04.dto;

import com.sparta.hanghae_homework_week04.domain.NoticeBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class NoticeBoardDto {

    private Long id;
    private String title;
    private String author;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public NoticeBoardDto(NoticeBoard noticeboard) {
        this.id = noticeboard.getId();
        this.title = noticeboard.getTitle();
        this.author = noticeboard.getAuthor();
        this.content = noticeboard.getContent();
        this.createdAt = noticeboard.getCreatedAt();
        this.modifiedAt = noticeboard.getModifiedAt();
    }
}
