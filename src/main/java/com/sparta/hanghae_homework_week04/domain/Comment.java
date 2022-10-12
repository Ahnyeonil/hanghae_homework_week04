package com.sparta.hanghae_homework_week04.domain;

import com.sparta.hanghae_homework_week04.util.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments")
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "notice_noard_id")
    private NoticeBoard noticeBoard;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

/*    public Comment(CommentRequestDto commentRequestDto, User user, NoticeBoard noticeBoard) {
        this.comment = commentRequestDto.getComment();
        this.noticeBoard = noticeBoard;
        this.user = user;

    }*/
}
