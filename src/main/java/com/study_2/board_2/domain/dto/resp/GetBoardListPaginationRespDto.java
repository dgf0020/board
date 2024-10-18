package com.study_2.board_2.domain.dto.resp;

import com.study_2.board_2.domain.entity.Board;
import lombok.Builder;

import java.util.List;

@Builder
public record GetBoardListPaginationRespDto(
        int currentPage,
        int totalPages,
        List<GetBoardRespDto> boardList
) {
}
