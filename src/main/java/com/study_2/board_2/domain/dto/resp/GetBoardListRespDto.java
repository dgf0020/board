package com.study_2.board_2.domain.dto.resp;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record GetBoardListRespDto(
        Long id,
        String title,
        String author,
        LocalDateTime created_date
) {
}
