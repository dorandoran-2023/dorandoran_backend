package com.dorandoran.dorandoran.core.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.dorandoran.dorandoran.core.user.domain.PhoneNumberAuthentication;

public interface PhoneNumberAuthenticationRepository extends CrudRepository<PhoneNumberAuthentication, String> {
}
