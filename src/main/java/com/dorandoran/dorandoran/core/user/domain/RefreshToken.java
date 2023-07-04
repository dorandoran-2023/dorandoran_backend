package com.dorandoran.dorandoran.core.user.domain;

import static com.google.common.base.Preconditions.*;

import java.util.concurrent.TimeUnit;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import lombok.Getter;

@Getter
@RedisHash(value = "refresh_token")
public class RefreshToken {
	@Id
	private String refreshToken;

	private Long userId;

	@TimeToLive(unit = TimeUnit.DAYS)
	private Long expiration = 14L;

	public RefreshToken(String refreshToken, long userId) {
		checkNotNull(refreshToken, "RefreshToken.refreshToken cannot be null");
		this.refreshToken = refreshToken;
		this.userId = userId;
	}
}
