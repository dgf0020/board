package com.study_2.board_2.domain.board.dto.req;

import com.study_2.board_2.domain.board.entity.Board;

public record UpdateBoardReqDto(
        String title,
        String content
) {
    public Board of() {
        return Board.builder()
                .title(this.title)
                .content(this.content)
                .build();
    }
}
