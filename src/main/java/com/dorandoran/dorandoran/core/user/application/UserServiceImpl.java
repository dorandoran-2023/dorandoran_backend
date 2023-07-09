package com.dorandoran.dorandoran.core.user.application;

import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dorandoran.dorandoran.core.profile.application.ProfileService;
import com.dorandoran.dorandoran.core.profile.domain.Profile;
import com.dorandoran.dorandoran.core.profile.repository.ProfileRepository;
import com.dorandoran.dorandoran.core.user.domain.PhoneNumberAuthentication;
import com.dorandoran.dorandoran.core.user.domain.RefreshToken;
import com.dorandoran.dorandoran.core.user.domain.User;
import com.dorandoran.dorandoran.core.user.dto.AccessTokenCheckRequest;
import com.dorandoran.dorandoran.core.user.dto.AccessTokenCheckResponse;
import com.dorandoran.dorandoran.core.user.dto.AccessTokenReissueRequest;
import com.dorandoran.dorandoran.core.user.dto.AccessTokenReissueResponse;
import com.dorandoran.dorandoran.core.user.dto.AddUserRequest;
import com.dorandoran.dorandoran.core.user.dto.AddUserResponse;
import com.dorandoran.dorandoran.core.user.dto.EmailDuplicatedCheckRequest;
import com.dorandoran.dorandoran.core.user.dto.EmailDuplicatedCheckResponse;
import com.dorandoran.dorandoran.core.user.dto.LoginRequest;
import com.dorandoran.dorandoran.core.user.dto.LoginResponse;
import com.dorandoran.dorandoran.core.user.dto.PhoneNumberAuthenticationCodeRequest;
import com.dorandoran.dorandoran.core.user.dto.PhoneNumberAuthenticationRequest;
import com.dorandoran.dorandoran.core.user.repository.PhoneNumberAuthenticationRepository;
import com.dorandoran.dorandoran.core.user.repository.RefreshTokenRepository;
import com.dorandoran.dorandoran.core.user.repository.UserRepository;
import com.dorandoran.dorandoran.global.error.exception.BadRequestException;
import com.dorandoran.dorandoran.global.error.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {
	private final SmsService smsService;
	private final ProfileService profileService;
	private final TokenService tokenService;
	private final PhoneNumberAuthenticationRepository phoneNumberAuthenticationRepository;
	private final UserRepository userRepository;
	private final ProfileRepository profileRepository;
	private final RefreshTokenRepository refreshTokenRepository;

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
		profileService.createProfile(user);

		// return
		return AddUserResponse.ofEntity(savedUser);
	}

	@Override
	public LoginResponse login(LoginRequest request) {
		// find user
		User user = userRepository.findByEmail(request.getEmail())
			.orElseThrow(() -> new NotFoundException("user", request.getEmail().getValue()));

		// check if password matches
		if (!request.getPassword().matches(user.getPassword())) {
			throw new BadRequestException("signIn-failed.password-missmatch");
		}

		// find profileId
		Long profileId = profileRepository.findProfileIdByUserId(user.getId())
			.orElseThrow(() -> new NotFoundException("profile", user.getId()));

		// create token
		String accessToken = tokenService.createAccessToken(user.getId(), profileId);
		String refreshToken = tokenService.createRefreshToken();

		// save refreshToken
		saveRefreshToken(refreshToken, user.getId());

		// create & return response
		return new LoginResponse(accessToken, refreshToken);
	}

	@Override
	public AccessTokenCheckResponse checkTokenExpiration(AccessTokenCheckRequest request) {
		// check if expired
		boolean expired = tokenService.isExpired(request.getAccessToken());

		// create & return response
		return new AccessTokenCheckResponse(expired);
	}

	@Override
	public AccessTokenReissueResponse reissueToken(AccessTokenReissueRequest request) {
		// check if expired
		if (tokenService.isExpired(request.getRefreshToken())) {
			throw new BadRequestException("token-reissue-failed.refreshToken-expired");
		}

		// find refreshToken
		RefreshToken refreshToken = refreshTokenRepository.findById(request.getRefreshToken())
			.orElseThrow(() -> new BadRequestException("token-reissue-failed.refreshTokne-notFound"));

		// find profile & user
		Profile profile = profileRepository.findByUserId(refreshToken.getUserId())
			.orElseThrow(() -> new BadRequestException("token-reissue-failed.userId-notFound"));
		User user = profile.getUser();

		// access token & refresh token reissue
		String accessToken = tokenService.createAccessToken(user.getId(), profile.getId());
		String reissuedRefreshToken = tokenService.createRefreshToken();

		// delete & save refreshToken
		deleteRefreshToken(refreshToken.getRefreshToken());
		saveRefreshToken(reissuedRefreshToken, user.getId());

		// create & return response
		return new AccessTokenReissueResponse(accessToken, reissuedRefreshToken);
	}

	private void saveRefreshToken(String refreshTokenStr, long userId) {
		RefreshToken refreshToken = new RefreshToken(refreshTokenStr, userId);
		refreshTokenRepository.save(refreshToken);
	}

	private void deleteRefreshToken(String refreshTokenStr) {
		refreshTokenRepository.deleteById(refreshTokenStr);
	}
}
