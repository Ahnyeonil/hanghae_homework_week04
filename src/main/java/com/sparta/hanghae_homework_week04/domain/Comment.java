package com.sparta.hanghae_homework_week04.domain;

import com.sparta.hanghae_homework_week04.dto.CommentDto;
import com.sparta.hanghae_homework_week04.util.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "id")
    private NoticeBoard boardId;

    @ManyToOne
    @JoinColumn(name = "nickname")
    private User nickname;

    public Comment(CommentDto commentDto) {
        this.comment = commentDto.getComment();
        this.boardId = commentDto.getBoardId();
        this.nickname = commentDto.getNickname();

    }

    public Comment(String comment, NoticeBoard boardId, User nickname) {
        this.comment = comment;
        this.boardId = boardId;
        this.nickname = nickname;
    }
}
