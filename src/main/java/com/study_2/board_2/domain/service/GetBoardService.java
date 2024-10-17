package com.study_2.board_2.domain.service;

import com.study_2.board_2.domain.dto.resp.GetBoardRespDto;
import com.study_2.board_2.global.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class GetBoardService {

    private final BoardMapper boardMapper;

    public GetBoardRespDto getBoard(Long id) {
        return boardMapper.getBoard(id).of();
    }
}
