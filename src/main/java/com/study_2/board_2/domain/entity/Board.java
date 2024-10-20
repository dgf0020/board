package com.study_2.board_2.domain.entity;

import com.study_2.board_2.domain.dto.resp.GetBoardRespDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
public class Board {
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public GetBoardRespDto of() {
    // entity -> Dto로 변환
        return GetBoardRespDto.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .author(this.author)
                .created_date(this.createdDate)
                .updated_date(this.updatedDate)
                .build();
    }
}
