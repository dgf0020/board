package com.study_2.board_2.domain.service;

import com.study_2.board_2.domain.dto.req.UpdateBoardReqDto;
import com.study_2.board_2.domain.entity.Board;
import com.study_2.board_2.domain.entity.repository.BoardRepository;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class UpdateBoardService {

//    private final BoardMapper boardMapper;
    private final BoardRepository boardRepository;

    public void updateBoard(Long id, UpdateBoardReqDto req) {

        Optional<Board> optionalBoard = boardRepository.findById(id);

        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();

            board.updateBoard(req.title(), req.content());

            boardRepository.save(board);
            // save는 db에 해당 id의 값이 없으면 생성해주고,
            // 동일한 id가 있는 경우는 update 해준다

        } else {
            throw new NoSuchElementException("해당 ID와 일치하는 게시글이 존재하지 않습니다.");
        }

        /*
        Board boardToUpdate = req.of();
        // 전달받은 id값을 설정하기위해 board 생성

        boardToUpdate.setId(id);
        // setId를 통해 id값 설정

        boardMapper.updateBoard(boardToUpdate);
        // id, title, content값이 설정된 board객체를 가지고 mapper로 간다
        */
    }
}
