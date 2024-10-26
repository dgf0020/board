package com.study_2.board_2.domain.user.service;

import com.study_2.board_2.domain.user.dto.req.LoginUserReqDto;
import com.study_2.board_2.domain.user.entity.User;
import com.study_2.board_2.domain.user.entity.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class LoginUserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  public void loginUser(LoginUserReqDto req, HttpSession session) {
    User byUsername = userRepository.findByUsername(req.username());

    if (byUsername == null || !passwordEncoder.matches(req.rawPassword(), byUsername.getPassword())) {
      throw new RuntimeException("로그인 실패");
    }

    session.setAttribute("user", byUsername);
  }
}
