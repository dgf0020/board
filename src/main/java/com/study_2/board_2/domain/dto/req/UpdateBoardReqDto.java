package com.study_2.board_2.domain.dto.req;

import com.study_2.board_2.domain.entity.Board;

import java.time.LocalDateTime;

public record UpdateBoardReqDto(
        String title,
        String content
) {
    public Board of() {
        return Board.builder()
                .title(this.title)
                .content(this.content)
                .updatedDate(LocalDateTime.now())
                .build();
    }
}
