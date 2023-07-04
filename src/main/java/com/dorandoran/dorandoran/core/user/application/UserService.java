package com.dorandoran.dorandoran.core.user.application;

import com.dorandoran.dorandoran.core.user.dto.AddUserRequest;
import com.dorandoran.dorandoran.core.user.dto.AddUserResponse;
import com.dorandoran.dorandoran.core.user.dto.EmailDuplicatedCheckRequest;
import com.dorandoran.dorandoran.core.user.dto.EmailDuplicatedCheckResponse;
import com.dorandoran.dorandoran.core.user.dto.LoginRequest;
import com.dorandoran.dorandoran.core.user.dto.LoginResponse;
import com.dorandoran.dorandoran.core.user.dto.PhoneNumberAuthenticationCodeRequest;
import com.dorandoran.dorandoran.core.user.dto.PhoneNumberAuthenticationRequest;

public interface UserService {
	void sendAuthenticationCode(PhoneNumberAuthenticationCodeRequest request);

	void authenticatePhoneNumber(PhoneNumberAuthenticationRequest request);

	EmailDuplicatedCheckResponse checkEmailDuplicated(EmailDuplicatedCheckRequest request);

	AddUserResponse addUser(AddUserRequest request);

	LoginResponse login(LoginRequest request);
}
