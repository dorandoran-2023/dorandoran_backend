package com.dorandoran.dorandoran.core.user.application;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dorandoran.dorandoran.core.user.domain.PhoneNumberAuthentication;
import com.dorandoran.dorandoran.core.user.domain.User;
import com.dorandoran.dorandoran.core.user.dto.AddUserRequest;
import com.dorandoran.dorandoran.core.user.dto.AddUserResponse;
import com.dorandoran.dorandoran.core.user.dto.EmailDuplicatedCheckRequest;
import com.dorandoran.dorandoran.core.user.dto.EmailDuplicatedCheckResponse;
import com.dorandoran.dorandoran.core.user.dto.PhoneNumberAuthenticationCodeRequest;
import com.dorandoran.dorandoran.core.user.dto.PhoneNumberAuthenticationRequest;
import com.dorandoran.dorandoran.core.user.repository.PhoneNumberAuthenticationRepository;
import com.dorandoran.dorandoran.core.user.repository.UserRepository;
import com.dorandoran.dorandoran.global.error.exception.BadRequestException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {
	private final SmsService smsService;
	private final PhoneNumberAuthenticationRepository phoneNumberAuthenticationRepository;
	private final UserRepository userRepository;

	@Override
	@Transactional
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
	@Transactional
	public void authenticatePhoneNumber(PhoneNumberAuthenticationRequest request) {
		// compare certification code
		String phoneNumber = request.getPhoneNumber().replace("-", "").trim();
		PhoneNumberAuthentication authentication = phoneNumberAuthenticationRepository.findById(phoneNumber)
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

	@Override
	public EmailDuplicatedCheckResponse checkEmailDuplicated(EmailDuplicatedCheckRequest request) {
		// check if duplicated
		boolean exists = userRepository.existsByEmail(request.getEmail());

		// return
		return new EmailDuplicatedCheckResponse(exists);
	}

	@Override
	@Transactional
	public AddUserResponse addUser(AddUserRequest request) {
		// validate password
		if (!request.getPassword().getValue().equals(request.getCheckingPassword().getValue())) {
			throw new BadRequestException("signIn-failed.password-missmatch");
		}

		// create user
		User user = new User(
			request.getEmail(),
			request.getPassword(),
			request.getUserType()
		);

		// save user
		User savedUser = userRepository.save(user);

		// return
		return AddUserResponse.ofEntity(savedUser);
	}
}
