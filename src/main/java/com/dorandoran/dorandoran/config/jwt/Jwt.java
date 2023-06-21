package com.dorandoran.dorandoran.config.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;

import com.dorandoran.dorandoran.global.model.SecurityUser;

public class Jwt extends AbstractAuthenticationToken {
	private final Object principal;

	public Jwt(String principal) {
		super(null);
		this.principal = principal;
		setAuthenticated(false);
	}

	public Jwt(SecurityUser principal) {
		super(AuthorityUtils.NO_AUTHORITIES);
		this.principal = principal;
		super.setAuthenticated(true);
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}
}