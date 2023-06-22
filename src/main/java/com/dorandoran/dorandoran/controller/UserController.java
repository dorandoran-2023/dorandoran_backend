package com.dorandoran.dorandoran.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dorandoran.dorandoran.core.user.application.UserService;
import com.dorandoran.dorandoran.core.user.dto.PhoneNumberAuthenticationCodeRequest;
import com.dorandoran.dorandoran.core.user.dto.PhoneNumberAuthenticationRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/users")
public class UserController {
	private final UserService userService;

	@PostMapping("/authentication/phone_number/code")
	public void sendAuthenticationCode(
		PhoneNumberAuthenticationCodeRequest request
	) {
		userService.sendAuthenticationCode(request);
	}

	@PostMapping("/authentication/phone_number")
	public void authenticatePhoneNumber(
		PhoneNumberAuthenticationRequest request
	) {
		userService.authenticatePhoneNumber(request);
	}
}
