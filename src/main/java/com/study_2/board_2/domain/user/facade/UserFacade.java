package com.study_2.board_2.domain.user.facade;

import com.study_2.board_2.domain.user.dto.req.CreateUserReqDto;
import com.study_2.board_2.domain.user.dto.req.LoginUserReqDto;
import com.study_2.board_2.domain.user.service.CreateUserService;
import com.study_2.board_2.domain.user.service.LoginUserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

  private final CreateUserService createUserService;
  private final LoginUserService loginUserService;

  public void createUser(CreateUserReqDto req) {
    createUserService.createUser(req);
  }

  // loginUserService.loginUser(req, session);
  public void loginUser(LoginUserReqDto req, HttpSession session) {
    loginUserService.loginUser(req, session);
  }
}
