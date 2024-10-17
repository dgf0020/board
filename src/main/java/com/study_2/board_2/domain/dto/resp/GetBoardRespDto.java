package com.study_2.board_2.domain.dto.resp;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record GetBoardRespDto(
        Long id,
        String title,
        String content,
        String author,
        LocalDateTime created_date,
        LocalDateTime updated_date
        // 결과로 보여줄 필드들 (필요없는 필드는 삭제해도 된다)
) {
}
