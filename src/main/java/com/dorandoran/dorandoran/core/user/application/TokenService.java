package com.dorandoran.dorandoran.core.user.application;

import java.util.Map;

public interface TokenService {
	Map<String, Object> parseClaim(String token);

	String createAccessToken(long userId, long profileId);

	String createRefreshToken();

	boolean isExpired(String token);
}
