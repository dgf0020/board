package com.study_2.board_2.domain.service;

import com.study_2.board_2.global.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class DeleteBoardService {
    private final BoardMapper boardMapper;

    public void deleteBoard(Long id) {
        boardMapper.deleteBoard(id);
    }
}
