package com.sparta.hanghae_homework_week04.repository;


import com.sparta.hanghae_homework_week04.domain.NoticeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeBoardRepository extends JpaRepository<NoticeBoard, Long> {

}
