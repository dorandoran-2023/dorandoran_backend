package com.dorandoran.dorandoran.core.user.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.dorandoran.dorandoran.core.user.dto.PhoneNumberAuthenticationCodeRequest;
import com.dorandoran.dorandoran.core.user.repository.PhoneNumberAuthenticationRepository;

@Transactional
@SpringBootTest
class UserServiceImplTest {
	@Autowired
	private UserServiceImpl sut;
	@Autowired
	private PhoneNumberAuthenticationRepository phoneNumberAuthenticationRepository;

	@Test
	void 핸드폰번호_인증코드전송_성공() {
		// given
		String phoneNumber = "010-5511-0625";
		PhoneNumberAuthenticationCodeRequest request = new PhoneNumberAuthenticationCodeRequest(phoneNumber);

		// when & then
		sut.sendAuthenticationCode(request);
	}
}
