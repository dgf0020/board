package com.study_2.board_2.domain.service;

import com.study_2.board_2.domain.dto.resp.GetBoardRespDto;
import com.study_2.board_2.domain.entity.Board;
import com.study_2.board_2.domain.entity.repository.BoardRepository;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class GetBoardService {

//    private final BoardMapper boardMapper;
    private final BoardRepository boardRepository;

    public GetBoardRespDto getBoard(Long id) {
//        return boardMapper.getBoard(id).of();

      Optional<Board> optionalBoard = boardRepository.findById(id);
      // findById() : 반환형이 Optional<T>이다
      // Optional<T> : 값이 들어있을수도, 비어있을수도있음

      if (optionalBoard.isPresent()) {
      // optionalBoard에 값이 들어있는 경우
        return optionalBoard.get().of();
      } else {
        throw new NoSuchElementException("해당 ID와 일치하는 게시글이 존재하지 않습니다.");
        // NoSuchElementException : Optional에 값이 비어있는 empty 상태일 때 .get()을 하면 발생하는 예외
      }
    }

    public List<GetBoardRespDto> getBoardList() {
      List<Board> boardList = boardRepository.findAll();

      return boardList.stream()
          .map(Board::of)
          .toList();
        /*
        List<Board> board = boardMapper.getBoardList();
        // boardMapper.getBoardList() 메서드를 호출 => board 테이블 전체를 가져옴
        // 그것을 List<Board> 타입의 board 변수에 저장한다.
        // (왜냐하면 boardMapper.getBoardList()의 반환형이 List<Board> != List<GetBoardRespDto>
        // 바로 return이 안된다)

        return board.stream()
                // 해당 리스트(List<Board>)를 스트림으로 변환.
                // 스트림을 사용하면 컬렉션의 요소들을 쉽게 필터링, 변환, 정렬할 수 있다.
                .map(Board::of)
                // map은 스트림의 각 요소를 변환할 때 사용
                // Board를 of(GetBoardRespDto)로 변환
                .toList();
        // 변환한 것들을 List 형태로 주워담아서 결국 List<GetBoardRespDto>가 된다
        */
    }
}
