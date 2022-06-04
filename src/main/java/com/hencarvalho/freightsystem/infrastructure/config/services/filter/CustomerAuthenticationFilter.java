package com.hencarvalho.freightsystem.infrastructure.config.services.filter;

import static com.hencarvalho.freightsystem.infrastructure.util.JWTUtils.TOKEN_REFRESH;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hencarvalho.freightsystem.infrastructure.util.JWTUtils;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@AllArgsConstructor(onConstructor_ = @Autowired)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  AuthenticationManager authenticationManager;

  Algorithm alg;

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

    final var params = getJsonParams(request);

    final var email = params.get("email");
    final var password = params.get("password");

    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(email, password);

    return authenticationManager.authenticate(authenticationToken);
  }

  @Override
  protected void successfulAuthentication(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain,
      Authentication authResult)
      throws IOException, ServletException {
    final var user = (User) authResult.getPrincipal();

    final var accessToken = generateJWTToken(user, request, alg);
    final var refreshToken = generateJWTRefreshToken(user, request, alg);

    response.setHeader("access_token", accessToken);
    response.setHeader("refresh_token", refreshToken);
  }

  private Map<String, String> getJsonParams(@NonNull final HttpServletRequest request) {
    try {
      final var params = new ObjectMapper().readValue(request.getInputStream(), Map.class);

      log.info("object decoded.");

      return params;
    } catch (Exception exception) {
      final var errorMessage = "Was not possible to decode the request";

      log.error(errorMessage, exception);

      throw new RuntimeException(errorMessage, exception);
    }
  }

  private String generateJWTToken(
      @NonNull final User user, final HttpServletRequest request, final Algorithm alg) {
    return JWT.create()
        .withSubject(user.getUsername())
        .withExpiresAt(new Date(System.currentTimeMillis() + JWTUtils.TOKEN_EXPIRATION))
        .withIssuer(request.getRequestURL().toString())
        .withClaim(
            JWTUtils.ROLE_FIELD_NAME,
            user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()))
        .sign(alg);
  }

  private String generateJWTRefreshToken(
      @NonNull final User user, final HttpServletRequest request, final Algorithm alg) {
    return JWT.create()
        .withSubject(user.getUsername())
        .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_REFRESH))
        .withIssuer(request.getRequestURL().toString())
        .sign(alg);
  }
}
