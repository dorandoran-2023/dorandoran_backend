package com.dorandoran.dorandoran.core.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dorandoran.dorandoran.core.user.domain.Email;
import com.dorandoran.dorandoran.core.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByEmail(Email email);

	Optional<User> findByEmail(Email email);
}
