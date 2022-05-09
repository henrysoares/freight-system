package com.hencarvalho.freightsystem.infrastructure.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JWTUtils {

  /** 300 minutos para a expiração do token. */
  public static final String ROLE_FIELD_NAME = "ROLE";

  public static final int TOKEN_EXPIRATION = 300 * 60 * 1000;
  public static final int TOKEN_REFRESH = 350 * 60 * 1000;
  public static final String SECRET = "1fbb47ce-9bd7-4a40-9178-90a8d8ad46a5";
  public static final String LOGIN_PATH = "/login";

  /**
   * Bean do algoritimo usado para fazer a encriptação dos dados.
   *
   * @return Uma nova instancia de {@link Algorithm}
   */
  @Bean
  public Algorithm getAlg() {
    return Algorithm.HMAC256(SECRET);
  }

  @Bean
  public JWTVerifier jWTVerifier() {
    final var alg = getAlg();
    return JWT.require(alg).build();
  }
}
