package com.study_2.board_2.domain.service;

import com.study_2.board_2.domain.dto.req.UpdateBoardReqDto;
import com.study_2.board_2.domain.entity.Board;
import com.study_2.board_2.global.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class UpdateBoardService {

    private final BoardMapper boardMapper;

    public void updateBoard(Long id, UpdateBoardReqDto req) {
        Board boardToUpdate = req.of();
        // 전달받은 id값을 설정하기위해 board 생성

        boardToUpdate.setId(id);
        // setId를 통해 id값 설정

        boardMapper.updateBoard(boardToUpdate);
        // id, title, content값이 설정된 board객체를 가지고 mapper로 간다
    }
}
