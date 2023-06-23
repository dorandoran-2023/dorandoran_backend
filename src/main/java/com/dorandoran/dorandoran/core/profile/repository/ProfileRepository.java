package com.dorandoran.dorandoran.core.profile.repository;

import com.dorandoran.dorandoran.core.profile.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
