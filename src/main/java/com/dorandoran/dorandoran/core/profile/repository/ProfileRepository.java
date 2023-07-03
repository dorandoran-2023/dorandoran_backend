package com.dorandoran.dorandoran.core.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dorandoran.dorandoran.core.profile.domain.Nickname;
import com.dorandoran.dorandoran.core.profile.domain.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    boolean existsByNickname(Nickname nickname);
}
