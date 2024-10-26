package com.study_2.board_2.domain.user.controller;

import com.study_2.board_2.domain.user.dto.req.CreateUserReqDto;
import com.study_2.board_2.domain.user.dto.req.LoginUserReqDto;
import com.study_2.board_2.domain.user.service.CreateUserService;
import com.study_2.board_2.domain.user.service.LoginUserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
@RequiredArgsConstructor
public class UserController {

  private final CreateUserService createUserService;
  private final LoginUserService loginUserService;

  @PostMapping
  public ResponseEntity<String> createUser(@RequestBody CreateUserReqDto req) {
    try {
      createUserService.createUser(req);
      return ResponseEntity.ok().body("유저 생성 완료");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping("/login")
  public ResponseEntity<String> loginUser(@RequestBody LoginUserReqDto req, HttpSession session) {
    try {
      loginUserService.loginUser(req, session);
      return ResponseEntity.ok().body("로그인 성공");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
