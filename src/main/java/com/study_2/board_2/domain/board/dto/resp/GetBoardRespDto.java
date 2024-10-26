package com.study_2.board_2.domain.board.dto.resp;

import com.study_2.board_2.domain.board.entity.Board;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record GetBoardRespDto(
        Long id,
        String title,
        String content,
        String author,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
        // 결과로 보여줄 필드들 (필요없는 필드는 삭제해도 된다)
) {

  public static GetBoardRespDto from(Board board) {
    return GetBoardRespDto.builder()
        .id(board.getId())
        .title(board.getTitle())
        .content(board.getContent())
        .author(board.getUser().getUsername())
        .createdDate(board.getCreatedDate())
        .updatedDate(board.getUpdatedDate())
        .build();
  }
}
