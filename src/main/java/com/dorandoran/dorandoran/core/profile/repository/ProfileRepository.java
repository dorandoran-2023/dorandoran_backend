package com.dorandoran.dorandoran.core.profile.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dorandoran.dorandoran.core.profile.domain.Nickname;
import com.dorandoran.dorandoran.core.profile.domain.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
	boolean existsByNickname(Nickname nickname);

	@Query("SELECT p.id "
		+ "FROM Profile  p "
		+ "WHERE p.user.id = :userId")
	Optional<Long> findProfileIdByUserId(@Param("userId") long userId);
}
