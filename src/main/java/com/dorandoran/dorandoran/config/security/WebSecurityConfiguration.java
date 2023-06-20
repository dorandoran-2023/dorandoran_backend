package com.dorandoran.dorandoran.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dorandoran.dorandoran.config.jwt.JwtAuthenticationFilter;
import com.dorandoran.dorandoran.core.user.domain.Password;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
public class WebSecurityConfiguration {
	static {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		// set encoder as simple delegator of BCryptPasswordEncoder
		Password.setEncoder(
			new Password.Encoder() {
				@Override
				public String encode(String rawPassword) {
					return passwordEncoder.encode(rawPassword);
				}

				@Override
				public boolean matches(String rawPassword, String encodedPassword) {
					return passwordEncoder.matches(rawPassword, encodedPassword);
				}
			}
		);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(
		HttpSecurity http,
		JwtAuthenticationFilter jwtAuthenticationFilter,
		AuthenticationEntryPoint authenticationEntryPoint
	) throws Exception {
		return http
			.csrf().disable()
			.formLogin().disable()
			.httpBasic().disable()
			.headers().disable()
			.cors().and()
			.sessionManagement(
				session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			)
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests(
				authorize -> authorize
					.antMatchers("/v1/signup/**", "/v1/login/**").permitAll()
					.anyRequest().authenticated()
			)
			.exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint))
			.build();
	}
}
