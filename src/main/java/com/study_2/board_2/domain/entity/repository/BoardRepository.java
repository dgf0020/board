package com.study_2.board_2.domain.entity.repository;

import com.study_2.board_2.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
// JpaRepository<Board, Long> : db의 Board 테이블에 매핑, Board 테이블의 pk인 id가 Long 타입
// JpaRepository를 상속받으면 기본 CRUD 메서드(save,findById, findAll, deleteById)를 자동으로 사용할 수 있다
}
