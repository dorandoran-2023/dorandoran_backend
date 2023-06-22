package com.dorandoran.dorandoran.core.user.application;

import java.util.Map;

public interface TokenService {
	Map<String, Object> parseClaim(String token);

	//TODO - createAccessToken(Profile profile)
	String createRefreshToken();

	boolean isExpired(String token);
}
