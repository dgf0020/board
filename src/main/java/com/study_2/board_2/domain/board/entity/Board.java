package com.study_2.board_2.domain.board.entity;

import com.study_2.board_2.domain.board.dto.resp.GetBoardRespDto;
import com.study_2.board_2.domain.user.entity.User;
import com.study_2.board_2.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Comment;

@Getter
// @Setter
// 엔티티의 값을 무분별하게 변경하지 않도록 Setter 생략
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
// @Builder, @Entity를 같이 쓸 때는
// @NoArgsConstructor, @AllArgsConstructor 이 두가지도 추가해서 써야한다
@Table(name = "boards")
// JPA에서 테이블이 자동으로 생성될 때 boards라는 이름으로 생성이 되고
// 지금 이 엔티티는 boards 테이블과 매핑된다
public class Board extends BaseTimeEntity {

    // 필드에 어노테이션들 jakarta.persistence인걸로 import 해야한다!!

    @Id
    // @Id : 해당 필드가 pk임을 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // db에 새 엔티티가 추가될 때마다 값을 자동으로 생성함 (그 값이 자동증가하는 방식)
    // GenerationType.IDENTITY : db에서 auto-increment 자동증가할 때 쓰임
    private Long id;

    @Column(name = "title", nullable = false)
    // Column : 해당 필드가 db 테이블의 특정 열과 매핑되도록 함
    // (name = "title") : db 테이블의 열 중에서도 title과 매핑
    // 여기서는 name 속성 값과 필드명이 같아서 생략해도 된다
    // (기본적으로 필드명과 같은 이름의 칼럼을 db 테이블에 매핑해줌)
    @Comment("게시글 제목")
    // 필드에 대한 설명 추가. db 구조에는 영향 x.(다른사람들이 보고 이해할 수 있도록)
    private String title;

    @Column(name = "content")
    @Comment("게시글 내용")
    private String content;

    @ManyToOne
    // 엔티티인 board가 many, 필드인 user가 one
    // => 여러개의 board객체가 하나의 user객체와 연결
    // (유저 한명이 게시글 여러개를 작성할 수 있다)
    @JoinColumn(name = "user_id")
    private User user;

//    private LocalDateTime createdDate;
//    private LocalDateTime updatedDate;
// JPA를 사용하면 createdDate,updatedDate 자동으로 만들어짐
// (BaseTimeEntity 만들고, BoardApplication에 어노테이션 추가했기때문에)

    public GetBoardRespDto of() {
    // entity -> Dto로 변환
        return GetBoardRespDto.builder()
            .id(this.id)
            .title(this.title)
            .content(this.content)
            .author(this.user.getUsername())
            .createdDate(this.getCreatedDate())
            .updatedDate(this.getUpdatedDate())
            .build();
    }

    public void updateBoard(String title, String content) {
        // Setter 대신 update를 하기위해 필요한 값만 받아와서 update
        this.title = title;
        this.content = content;
    }
}
