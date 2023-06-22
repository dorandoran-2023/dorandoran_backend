package com.dorandoran.dorandoran.core.user.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class UserServiceImplTest {
	@Autowired
	private UserServiceImpl sut;
}
