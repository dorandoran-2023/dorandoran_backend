package com.dorandoran.dorandoran.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dorandoran.dorandoran.core.user.application.UserService;
import com.dorandoran.dorandoran.core.user.dto.PhoneNumberAuthenticationCodeRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/users")
public class UserController {
	private final UserService userService;

	@PostMapping("/authentication/phone_number")
	public void sendAuthenticationCode(
		PhoneNumberAuthenticationCodeRequest request
	) {
		userService.sendAuthenticationCode(request);
	}
}
