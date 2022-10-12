package com.sparta.hanghae_homework_week04.service;

import com.sparta.hanghae_homework_week04.domain.NoticeBoard;
import com.sparta.hanghae_homework_week04.dto.NoticeBoardDto;
import com.sparta.hanghae_homework_week04.repository.NoticeBoardRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeBoardService {

    private final NoticeBoardRepository noticeBoardRepository;

    public List<NoticeBoardDto> findAll() {

        List<NoticeBoard> noticeBoards = noticeBoardRepository.findAll();
        List<NoticeBoardDto> noticeBoardDtos = new ArrayList<>();

        for (NoticeBoard noticeBoard : noticeBoards) {

            NoticeBoardDto boardDto = new NoticeBoardDto(noticeBoard);

            noticeBoardDtos.add(boardDto);
        }

        return noticeBoardDtos;
    }

    public NoticeBoardDto getPost(Long id) {

        NoticeBoard noticeBoard = noticeBoardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Board ID does not exist"));

        NoticeBoardDto noticeBoardDto = new NoticeBoardDto(noticeBoard);

        return noticeBoardDto;

    }

    public void save(NoticeBoardDto noticeBoardDto) {

        NoticeBoard board = new NoticeBoard(noticeBoardDto);

        noticeBoardRepository.save(board);
    }

    @Transactional
    public Long update(NoticeBoardDto requestDto, long requestId) {

        NoticeBoard noticeBoard = noticeBoardRepository.findById(requestId).orElseThrow(
                () -> new RuntimeException("Post ID does not exist")
        );

        noticeBoard.updateNoticeBoard(requestDto.getTitle(), requestDto.getAuthor(), requestDto.getContent());


        return noticeBoard.getId();
    }

    @Transactional
    public Long deletePost(Long id) {

        noticeBoardRepository.deleteById(id);

        return id;
    }
}
