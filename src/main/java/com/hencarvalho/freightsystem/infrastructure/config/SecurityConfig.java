package com.hencarvalho.freightsystem.infrastructure.config;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.hencarvalho.freightsystem.application.services.filter.CustomerAuthenticationFilter;
import com.hencarvalho.freightsystem.application.services.filter.CustomerAuthorizationFilter;
import com.hencarvalho.freightsystem.infrastructure.util.CustomerType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/** Classe responsavel por fazer as configurações do Spring security */
@EnableWebSecurity
@AllArgsConstructor(onConstructor_ = @Autowired)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  UserDetailsService userDetailsService;

  PasswordEncoder passwordEncoder;

  Algorithm algorithm;

  JWTVerifier jwtVerifier;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.authorizeRequests()
        .antMatchers(HttpMethod.GET, "/api/user")
        .hasAnyRole(CustomerType.DRIVER.name(), CustomerType.REQUESTER.name());
    http.authorizeRequests().antMatchers(HttpMethod.POST, "/login").permitAll();
    http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/user/driver").permitAll();
    http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/user/requester").permitAll();
    http.authorizeRequests().anyRequest().authenticated();
    http.addFilter(new CustomerAuthenticationFilter(authenticationManagerBean(), algorithm));
    http.addFilterBefore(
        new CustomerAuthorizationFilter(jwtVerifier), UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
