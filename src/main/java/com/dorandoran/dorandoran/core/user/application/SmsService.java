package com.dorandoran.dorandoran.core.user.application;

public interface SmsService {
	void sendAuthenticationCode(String phoneNumber, String code);
}
