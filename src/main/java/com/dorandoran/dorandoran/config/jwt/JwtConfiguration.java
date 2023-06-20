package com.dorandoran.dorandoran.config.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dorandoran.dorandoran.core.user.application.TokenService;
import com.dorandoran.dorandoran.infra.jwt.JwtTokenService;

@Configuration(proxyBeanMethods = false)
public class JwtConfiguration {
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter(TokenService service) {
		return new JwtAuthenticationFilter(service);
	}

	@Bean
	public JwtTokenService jwtTokenService(JwtProperties properties) {
		return new JwtTokenService(properties);
	}
}
