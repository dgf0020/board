package com.study_2.board_2.domain.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
@RequiredArgsConstructor
public class SecurityController {
  private final SecurityService securityService;

  @GetMapping("/create/token")
  public ResponseEntity<?> createToken(@RequestParam(value = "subject") String subject) {
    String token = securityService.createToken(subject, (2*1000*60));
                                                      // 만료시간(2*1000*60) = 2분으로 임의 지정
                                                      // 2분이 지나면 해당 token은 만료된다 => (ExpiredJwtException)
    return ResponseEntity.ok().body(token);
    // 클라이언트가 입력한 subject와 secret key 등을 이용해서 token이 생성된다.
  }

  @GetMapping("/get/subject")
  public ResponseEntity<?> getSubject(@RequestParam(value = "token") String token) {
    String subject = securityService.getSubject(token);
    return ResponseEntity.ok().body(subject);
    // 위에서 만든 token을 입력하게되면 subject 원본을 얻을 수 있다.
  }
}

/*
security를 사용하려면 먼저 build.gradle에 의존성 추가해야한다. (3가지)
implementation 'io.jsonwebtoken:jjwt-api:0.11.5'
runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.5'
runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.11.5'

createToken 실행해서 실제로 생성된 token(JWT)을 보면 .으로 구분
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ3d2tkbHMiLCJleHAiOjE3MzIwMzg2MDh9.Jdid4Vcn_HaMV3Ax6Pkt4DEdAZ2cx55qyFzjLrWvPMU
      header                    payload                                 signature 로 이루어져있다.
*/