package com.study_2.board_2.domain.board.service;

import com.study_2.board_2.domain.board.dto.resp.GetBoardRespDto;
import com.study_2.board_2.domain.board.entity.Board;
import com.study_2.board_2.domain.board.entity.repository.BoardRepository;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class GetBoardService {

  //    private final BoardMapper boardMapper;
  private final BoardRepository boardRepository;

  public Board getBoard(Long id) {

    return boardRepository.findById(id)
      .orElseThrow(() -> new NoSuchElementException("해당 ID와 일치하는 게시글이 존재하지 않습니다."));
  }

  public List<Board> getBoardList() {

    return boardRepository.findAll();
  }

  // pagination도 GetBoardService에 같이 하자!
  public Page<Board> getBoardListPagination(int pageNo, int pageSize, String sortBy, String direction) {
  // pageNo, pageSize 외에도 정렬방식 등을 받을 수도 있다.

    Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.fromString(direction), sortBy));

    return boardRepository.findAll(pageable);
    //return boardRepository.findAllByOrderByCreatedDateDesc(pageable).map(GetBoardRespDto::from);
  }

  public List<Board> getBoardListSorted() {
    return boardRepository.findAll(Sort.by(Direction.ASC, "title"));
                                  // title을 기준으로 ASC 정렬
  }
}
