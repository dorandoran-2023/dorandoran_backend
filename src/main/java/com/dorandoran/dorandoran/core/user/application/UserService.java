package com.dorandoran.dorandoran.core.user.application;

import com.dorandoran.dorandoran.core.user.dto.PhoneNumberAuthenticationCodeRequest;
import com.dorandoran.dorandoran.core.user.dto.PhoneNumberAuthenticationRequest;

public interface UserService {
	void sendAuthenticationCode(PhoneNumberAuthenticationCodeRequest request);

	void authenticatePhoneNumber(PhoneNumberAuthenticationRequest request);
}
