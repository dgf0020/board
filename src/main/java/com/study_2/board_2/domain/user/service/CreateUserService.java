package com.study_2.board_2.domain.user.service;

import com.study_2.board_2.domain.user.dto.req.CreateUserReqDto;
import com.study_2.board_2.domain.user.entity.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class CreateUserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  public void createUser(CreateUserReqDto req) {
    userRepository.save(req.of(passwordEncoder.encode(req.password())));
  }
}
