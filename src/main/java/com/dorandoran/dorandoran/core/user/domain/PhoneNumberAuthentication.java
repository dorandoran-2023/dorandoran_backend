package com.dorandoran.dorandoran.core.user.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Getter;

@Getter
@RedisHash(value = "authentication", timeToLive = 300)
public class PhoneNumberAuthentication {
	@Id
	private String phoneNumber;
	private String authenticationCode;
	private boolean authenticated;

	public PhoneNumberAuthentication(String phoneNumber, String authenticationCode, boolean authenticated) {
		this.phoneNumber = phoneNumber;
		this.authenticationCode = authenticationCode;
		this.authenticated = authenticated;
	}
}
