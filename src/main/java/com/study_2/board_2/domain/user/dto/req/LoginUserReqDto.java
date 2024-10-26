package com.study_2.board_2.domain.user.dto.req;

import com.study_2.board_2.domain.user.entity.User;

public record LoginUserReqDto(
    String username,
    String rawPassword
) {
  public User of(String password) {
    return User.builder()
        .username(this.username)
        .password(password)
        .build();
  }
}
