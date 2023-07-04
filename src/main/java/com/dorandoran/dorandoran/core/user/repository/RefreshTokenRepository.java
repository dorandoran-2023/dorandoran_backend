package com.dorandoran.dorandoran.core.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.dorandoran.dorandoran.core.user.domain.RefreshToken;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
