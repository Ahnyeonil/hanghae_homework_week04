package com.sparta.hanghae_homework_week04.domain;

import com.sparta.hanghae_homework_week04.dto.NoticeBoardDto;
import com.sparta.hanghae_homework_week04.util.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "notice_boards")
@NoArgsConstructor
public class NoticeBoard extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String title;
    private String author;
    private String content;

    @ManyToOne
    @JoinColumn(name="nickname")
    private User nickname;

    public NoticeBoard(NoticeBoardDto noticeBoardDto) {
        this.title = noticeBoardDto.getTitle();
        this.author = noticeBoardDto.getAuthor();
        this.content = noticeBoardDto.getContent();
    }

    public void updateNoticeBoard(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }
}
