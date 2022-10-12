package com.sparta.hanghae_homework_week04.domain;

import com.sparta.hanghae_homework_week04.dto.NoticeBoardRequestDto;
import com.sparta.hanghae_homework_week04.util.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "notice_boards")
public class NoticeBoard extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String title;
    private String author;
    private String content;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public NoticeBoard(NoticeBoardRequestDto noticeBoardRequestDto, User user) {
        this.title = noticeBoardRequestDto.getTitle();
        this.author = noticeBoardRequestDto.getAuthor();
        this.content = noticeBoardRequestDto.getContent();
        this.user = user;
    }

    public void updateNoticeBoard(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }
}
