package com.study_2.board_2.domain.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
  private static final String SECRET_KEY = "SF98DFSD78VCSD768d7f6sd7f6s8d76f9a8df79s8d7f9s8df9s6d7f69sdV8SD78C7D7S78D8CS8D8CS8D8VDSV";
  // secret key에 임의로 아무거나 적기. 대신 너무 짧으면 안된다

  public String createToken(String subject, long expTime) {
    if (expTime <= 0) {
      throw new RuntimeException("만료시간은 0보다 큰 값이어야 합니다.");
    }

    // 어떤 알고리즘을 사용할 것인지
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
    Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());

    return Jwts.builder()
        .setSubject(subject)
        .signWith(signingKey, signatureAlgorithm)
        .setExpiration(new Date(System.currentTimeMillis() + expTime))
        .compact();
  }

  public String getSubject(String token) {
    Claims claims = Jwts.parserBuilder()
        .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
        .build()
        .parseClaimsJws(token)
        .getBody();

    return claims.getSubject();
    // claims에 담긴 여러 정보 중 subject만 가져오기
  }
}
