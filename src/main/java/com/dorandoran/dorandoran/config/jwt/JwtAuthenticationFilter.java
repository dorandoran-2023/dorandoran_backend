package com.dorandoran.dorandoran.config.jwt;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dorandoran.dorandoran.core.common.SecurityUser;
import com.dorandoran.dorandoran.core.user.application.TokenService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private final TokenService tokenService;

	@Override
	protected void doFilterInternal(
		HttpServletRequest request,
		HttpServletResponse response,
		FilterChain filterChain
	) throws ServletException, IOException {
		String token = request.getHeader("Authorization");
		if (token != null && token.startsWith("Bearer ")) {
			token = token.substring(7);

			Jwt unauthenticatedJwt = new Jwt(token);
			Jwt authenticatedJwt = authenticate(unauthenticatedJwt);

			log.debug("authenticate user");
			SecurityContextHolder.getContext().setAuthentication(authenticatedJwt);
		}
		filterChain.doFilter(request, response);
	}

	private Jwt authenticate(Jwt jwt) {
		Map<String, Object> claims = tokenService.parseClaim((String)jwt.getPrincipal());
		long userId = ((Integer)claims.get("userId")).longValue();
		long profileId = ((Integer)claims.get("profileId")).longValue();

		SecurityUser securityUser = new SecurityUser(userId, profileId);
		return new Jwt(securityUser);
	}
}
