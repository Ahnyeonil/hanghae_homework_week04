package com.sparta.hanghae_homework_week04.repository;

import com.sparta.hanghae_homework_week04.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    void deleteByNoticeBoard(long id);
}
