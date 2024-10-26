package com.study_2.board_2.domain.board.service;

import com.study_2.board_2.domain.board.entity.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class DeleteBoardService {
//    private final BoardMapper boardMapper;
    private final BoardRepository boardRepository;

    public void deleteBoard(Long id) {
        // boardMapper.deleteBoard(id);

        boardRepository.deleteById(id);
    }
}
