package com.study_2.board_2.domain.board.dto.resp;

import lombok.Builder;

import java.util.List;

@Builder
public record GetBoardListPaginationRespDto(
        int currentPage,
        int totalPages,
        List<GetBoardRespDto> boardList
) {
}
