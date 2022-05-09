package com.hencarvalho.freightsystem.application.services.filter;

import static com.hencarvalho.freightsystem.infrastructure.util.JWTUtils.LOGIN_PATH;
import static com.hencarvalho.freightsystem.infrastructure.util.JWTUtils.ROLE_FIELD_NAME;
import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.auth0.jwt.JWTVerifier;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@AllArgsConstructor(onConstructor_ = @Autowired)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerAuthorizationFilter extends OncePerRequestFilter {
  JWTVerifier jwtVerifier;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    if (request.getServletPath().equals(LOGIN_PATH)) {
      filterChain.doFilter(request, response);
    } else {
      final var authorizationHeader = request.getHeader(AUTHORIZATION);

      if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
        try {
          final var token = authorizationHeader.substring("Bearer ".length());

          var data = jwtVerifier.verify(token);

          var email = data.getSubject();
          final var roles = data.getClaim(ROLE_FIELD_NAME).asArray(String.class);

          Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

          stream(roles).forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));

          UsernamePasswordAuthenticationToken authenticationToken =
              new UsernamePasswordAuthenticationToken(email, null, authorities);

          SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        } catch (Exception exception) {
          log.error("was not possible to authorize the user", exception);
        }
      }
      filterChain.doFilter(request, response);
    }
  }
}
