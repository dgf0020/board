package com.study_2.board_2.global.entity;

/*
모든 엔티티는 생성시간, 수정시간이 필요
BaseTimeEntity는 JPA에서 공통적으로 사용되는 속성(생성시간, 수정시간)을
여러 엔티티에서 재사용하기위해 만듦
*/

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
// JPA가 알아서 생성시간과 수정시간을 업데이트해줌
public class BaseTimeEntity {

  @CreatedDate
  // @CreatedDate : 이 어노테이션이 붙은 필드는 엔티티가 처음 생성될 때의 시간을 자동으로 기록
  // (데이터베이스에 저장할 때의 현재 시간으로 설정됨)
  private LocalDateTime createdDate;

  @LastModifiedDate
  // @LastModifiedDate : 이 어노테이션이 붙은 필드는 엔티티가 수정될 때의 시간을 자동으로 기록
  // (엔티티가 업데이트될 때마다 현재 시간으로 설정됨)
  private LocalDateTime updatedDate;
}
