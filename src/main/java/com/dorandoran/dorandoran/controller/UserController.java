package com.dorandoran.dorandoran.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dorandoran.dorandoran.core.user.application.UserService;
import com.dorandoran.dorandoran.core.user.dto.AddUserRequest;
import com.dorandoran.dorandoran.core.user.dto.AddUserResponse;
import com.dorandoran.dorandoran.core.user.dto.EmailDuplicatedCheckRequest;
import com.dorandoran.dorandoran.core.user.dto.EmailDuplicatedCheckResponse;
import com.dorandoran.dorandoran.core.user.dto.PhoneNumberAuthenticationCodeRequest;
import com.dorandoran.dorandoran.core.user.dto.PhoneNumberAuthenticationRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserController {
	private final UserService userService;

	@PostMapping("/v1/signup/authentication/phone_number/code")
	public void sendAuthenticationCode(
		PhoneNumberAuthenticationCodeRequest request
	) {
		userService.sendAuthenticationCode(request);
	}

	@PostMapping("/v1/signup/authentication/phone_number")
	public void authenticatePhoneNumber(
		PhoneNumberAuthenticationRequest request
	) {
		userService.authenticatePhoneNumber(request);
	}

	@PostMapping("/v1/signup/email/duplicated")
	public EmailDuplicatedCheckResponse checkEmailDuplicated(
		EmailDuplicatedCheckRequest request
	) {
		return userService.checkEmailDuplicated(request);
	}

	@PostMapping("/v1/signup/users")
	public ResponseEntity<AddUserResponse> addUser(
		AddUserRequest request
	) {
		AddUserResponse body = userService.addUser(request);
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(body);
	}
}
