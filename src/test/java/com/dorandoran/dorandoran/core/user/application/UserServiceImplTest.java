package com.dorandoran.dorandoran.core.user.application;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.dorandoran.dorandoran.core.user.domain.Email;
import com.dorandoran.dorandoran.core.user.domain.Password;
import com.dorandoran.dorandoran.core.user.domain.UserType;
import com.dorandoran.dorandoran.core.user.dto.AddUserRequest;
import com.dorandoran.dorandoran.core.user.dto.AddUserResponse;
import com.dorandoran.dorandoran.core.user.dto.EmailDuplicatedCheckRequest;
import com.dorandoran.dorandoran.core.user.dto.EmailDuplicatedCheckResponse;
import com.dorandoran.dorandoran.global.error.exception.BadRequestException;

@Transactional
@SpringBootTest
class UserServiceImplTest {
	@Autowired
	private UserServiceImpl sut;

	@Test
	void 중복X_이메일중복체크_성공() {
		// given
		Email email = Email.of("test@world.com");
		EmailDuplicatedCheckRequest request = new EmailDuplicatedCheckRequest(email);

		// when
		EmailDuplicatedCheckResponse response = sut.checkEmailDuplicated(request);

		// then
		assertThat(response.isDuplicated()).isFalse();
	}

	@Test
	void 중복O_이메일중복체크_성공() {
		// given
		Email email = Email.of("test@world.com");
		Password password = Password.of("passw0rd");
		UserType userType = UserType.CLIENT;

		AddUserRequest addUserRequest = new AddUserRequest(email, password, password, userType);
		sut.addUser(addUserRequest);

		EmailDuplicatedCheckRequest request = new EmailDuplicatedCheckRequest(email);

		// when
		EmailDuplicatedCheckResponse response = sut.checkEmailDuplicated(request);

		// then
		assertThat(response.isDuplicated()).isTrue();
	}

	@Test
	void 회원가입_계정생성_성공() {
		// given
		Email email = Email.of("test@world.com");
		Password password = Password.of("passw0rd");
		UserType userType = UserType.CLIENT;

		AddUserRequest request = new AddUserRequest(email, password, password, userType);

		// when
		AddUserResponse response = sut.addUser(request);

		// then
		assertThat(response.getEmail()).isEqualTo(email.getValue());
		assertThat(response.getUserType()).isEqualTo(userType);
	}

	@Test
	void 회원가입_계정생성_예외성공() {
		// given
		Email email = Email.of("test@world.com");
		Password password = Password.of("passw0rd");
		UserType userType = UserType.CLIENT;

		AddUserRequest request = new AddUserRequest(email, password, Password.of("password"), userType);

		// when & then
		assertThatExceptionOfType(BadRequestException.class)
			.isThrownBy(() -> sut.addUser(request));
	}
}
