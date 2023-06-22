package com.dorandoran.dorandoran.core.user.application;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import com.dorandoran.dorandoran.core.user.domain.PhoneNumberAuthentication;
import com.dorandoran.dorandoran.core.user.dto.PhoneNumberAuthenticationCodeRequest;
import com.dorandoran.dorandoran.core.user.dto.PhoneNumberAuthenticationRequest;
import com.dorandoran.dorandoran.core.user.repository.PhoneNumberAuthenticationRepository;
import com.dorandoran.dorandoran.global.error.exception.BadRequestException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
	private final SmsService smsService;
	private final PhoneNumberAuthenticationRepository phoneNumberAuthenticationRepository;

	@Override
	public void sendAuthenticationCode(PhoneNumberAuthenticationCodeRequest request) {
		// get phone number and certification code
		String phoneNumber = request.getPhoneNumber().replace("-", "").trim();
		String certificationCode = RandomStringUtils.randomNumeric(6);

		// send certification code
		smsService.sendAuthenticationCode(phoneNumber, certificationCode);

		// put it to key-value store
		PhoneNumberAuthentication phoneNumberAuthentication
			= new PhoneNumberAuthentication(phoneNumber, certificationCode, false);
		phoneNumberAuthenticationRepository.save(phoneNumberAuthentication);
	}

	@Override
	public void authenticatePhoneNumber(PhoneNumberAuthenticationRequest request) {
		// compare certification code
		PhoneNumberAuthentication authentication = phoneNumberAuthenticationRepository.findById(request.getPhoneNumber())
			.orElseThrow(() -> new BadRequestException("phoneNumberAuthentication-failed.code-none"));

		if (authentication.isAuthenticated()) {
			throw new BadRequestException("phoneNumberAuthentication-failed.already-authenticated");
		}
		if (!authentication.getAuthenticationCode().equals(request.getCode())) {
			throw new BadRequestException("phoneNumberAuthentication-failed.code-missmatch");
		}

		// delete authentication from key-value store
		phoneNumberAuthenticationRepository.delete(authentication);
	}
}
