package com.sparta.hanghae_homework_week04.service;

import com.sparta.hanghae_homework_week04.domain.NoticeBoard;
import com.sparta.hanghae_homework_week04.domain.User;
import com.sparta.hanghae_homework_week04.dto.NoticeBoardRequestDto;
import com.sparta.hanghae_homework_week04.repository.CommentRepository;
import com.sparta.hanghae_homework_week04.repository.NoticeBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeBoardService {

    private final NoticeBoardRepository noticeBoardRepository;

    private final CommentRepository commentRepository;

    public List<NoticeBoardRequestDto> findAll() {

        List<NoticeBoard> noticeBoards = noticeBoardRepository.findAll();
        List<NoticeBoardRequestDto> noticeBoardRequestDtos = new ArrayList<>();

        for (NoticeBoard noticeBoard : noticeBoards) {

            NoticeBoardRequestDto boardDto = new NoticeBoardRequestDto(noticeBoard);

            noticeBoardRequestDtos.add(boardDto);
        }

        return noticeBoardRequestDtos;
    }

    public NoticeBoardRequestDto getPost(Long id) {

        NoticeBoard noticeBoard = noticeBoardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Board ID does not exist"));

        NoticeBoardRequestDto noticeBoardRequestDto = new NoticeBoardRequestDto(noticeBoard);

        return noticeBoardRequestDto;

    }

    public void save(NoticeBoardRequestDto noticeBoardRequestDto, User user) {

        NoticeBoard board = new NoticeBoard(noticeBoardRequestDto, user);

        noticeBoardRepository.save(board);
    }

    @Transactional
    public Long update(NoticeBoardRequestDto requestDto, long requestId, User user) {
        
        User updateUser = noticeBoardRepository.findById(requestId).get().getUser();
        
        if(!user.equals(updateUser)){
            throw new RuntimeException("게시글 작성자와 Login User가 일치하지 않습니다.");
        }

        NoticeBoard noticeBoard = noticeBoardRepository.findById(requestId).orElseThrow(
                () -> new RuntimeException("Post ID does not exist")
        );

        noticeBoard.updateNoticeBoard(requestDto.getTitle(), requestDto.getAuthor(), requestDto.getContent());


        return noticeBoard.getId();
    }

    @Transactional
    public Long deletePost(Long id, User user) {

        User deleteUser = noticeBoardRepository.findById(id).get().getUser();

        if(!user.equals(deleteUser)){
            throw new RuntimeException("게시글 작성자와 Login User가 일치하지 않습니다.");
        }

        noticeBoardRepository.deleteById(id);
        commentRepository.deleteByNoticeBoard(id);


        return id;
    }
}
