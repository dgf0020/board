package com.study_2.board_2.domain.user.controller;

import com.study_2.board_2.domain.user.dto.req.CreateUserReqDto;
import com.study_2.board_2.domain.user.dto.req.LoginUserReqDto;
import com.study_2.board_2.domain.user.facade.UserFacade;
import com.study_2.board_2.domain.user.service.CreateUserService;
import com.study_2.board_2.domain.user.service.LoginUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User")
@RestController("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserFacade userFacade;

  @Operation(summary = "유저 생성", description = "새로운 유저를 생성합니다. (회원가입)")
  @PostMapping
  public ResponseEntity<String> createUser(@RequestBody CreateUserReqDto req) {
    try {
      userFacade.createUser(req);
      return ResponseEntity.ok().body("유저 생성 완료");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @Operation(summary = "로그인")
  @PostMapping("/login")
  public ResponseEntity<String> loginUser(@RequestBody LoginUserReqDto req, HttpSession session) {
    try {
      userFacade.loginUser(req, session);
      return ResponseEntity.ok().body("로그인 성공");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @Operation(summary = "로그아웃")
  @PostMapping("/logout")
  public ResponseEntity<String> logoutUser(HttpSession session) {
    try {
      session.removeAttribute("user");
      return ResponseEntity.ok().body("로그아웃 성공");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
