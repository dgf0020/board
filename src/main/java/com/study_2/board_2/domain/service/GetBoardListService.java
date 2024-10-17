package com.study_2.board_2.domain.service;

import com.study_2.board_2.domain.dto.resp.GetBoardListRespDto;
import com.study_2.board_2.domain.entity.Board;
import com.study_2.board_2.global.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class GetBoardListService {

    private final BoardMapper boardMapper;

    public List<GetBoardListRespDto> getBoardList() {
    // 게시글 전체 목록을 받아오니까 List<GetBoardListRespDto>

        List<Board> board = boardMapper.getBoardList();
        // boardMapper.getBoardList() 메서드를 호출 => board 테이블 전체를 가져옴
        // 그것을 List<Board> 타입의 board 변수에 저장한다.
        // (왜냐하면 boardMapper.getBoardList()의 반환형이 List<Board> != List<GetBoardListRespDto>
        // 바로 return이 안된다)

        return board.stream()
        // 해당 리스트(List<Board>)를 스트림으로 변환.
        // 스트림을 사용하면 컬렉션의 요소들을 쉽게 필터링, 변환, 정렬할 수 있다.
                .map(Board::toDto)
                // map은 스트림의 각 요소를 변환할 때 사용
                // Board를 toDto(GetBoardListRespDto)로 변환
                .collect(Collectors.toList());
                // 변환한 것들을 List 형태로 주워담아서 결국 List<GetBoardListRespDto>가 된다
    }
}