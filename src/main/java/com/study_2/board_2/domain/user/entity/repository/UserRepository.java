package com.study_2.board_2.domain.user.entity.repository;

import com.study_2.board_2.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

  User findByUsername(String username);
}
