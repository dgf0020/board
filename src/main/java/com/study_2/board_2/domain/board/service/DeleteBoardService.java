package com.study_2.board_2.domain.board.service;

import com.study_2.board_2.domain.board.entity.Board;
import com.study_2.board_2.domain.board.entity.repository.BoardRepository;
import com.study_2.board_2.domain.user.entity.User;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class DeleteBoardService {
//    private final BoardMapper boardMapper;
    private final BoardRepository boardRepository;

    public void deleteBoard(Long id, User user) {
        Board board = boardRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("해당 ID와 일치하는 게시글이 존재하지 않습니다."));

        if (!board.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("삭제 권한이 없습니다.");
        }

        boardRepository.delete(board);
    }
}
