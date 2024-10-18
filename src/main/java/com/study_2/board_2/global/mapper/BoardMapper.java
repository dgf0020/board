package com.study_2.board_2.global.mapper;

import com.study_2.board_2.domain.entity.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    // 게시글 생성 (반환형 void)
    void createBoard(Board board);

    // 게시글 목록 조회 (List<Board>)
    List<Board> getBoardList();

    // 게시글 상세 조회 (Board)
    Board getBoard(Long id);

    // 게시글 업데이트 (void)
    void updateBoard(Board board);

    // 게시글 삭제 (void)
    void deleteBoard(Long id);

    // 총 게시글 수 (int)
    int getTotalPosts();

    // 게시글 목록 조회 Pagination (List<Board>)
    List<Board> getBoardListPagination(int pageSize, int offset);
}
