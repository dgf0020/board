package com.study_2.board_2.domain.dto.req;

import com.study_2.board_2.domain.entity.Board;

import java.time.LocalDateTime;

// record : java 15 이후로 나온 클래스
// 간결하고 불변성이 보장된 데이터 클래스를 만드는 데 유용, 코드의 가독성이 높아진다.
public record CreateBoardReqDto(
        String title,
        String content,
        String author
        // 클라이언트 -> 서버의 데이터 전송을 위해 필요한 최소한의 정보만 담는다.
) {
    public Board of() {
        // 클라이언트로부터 받아온 정보를 가지고 board 테이블에 행 추가하기위해
        return Board.builder()
                .title(this.title)
                .content(this.content)
                .author(this.author)
                .build();
    }
}
