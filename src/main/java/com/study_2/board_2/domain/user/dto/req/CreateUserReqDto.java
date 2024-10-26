package com.study_2.board_2.domain.user.dto.req;

import com.study_2.board_2.domain.user.entity.User;

public record CreateUserReqDto(
    String username,
    String password,
    String email,
    String name
) {
  public User of(String password) {
    return User.builder()
        .username(this.username)
        .password(password)
        .email(this.email)
        .name(this.name)
        .build();
  }
}
