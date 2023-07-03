package com.dorandoran.dorandoran.core.profile.application;

import com.dorandoran.dorandoran.core.common.SecurityUser;
import com.dorandoran.dorandoran.core.profile.dto.UpdateProfileRequest;
import com.dorandoran.dorandoran.core.profile.dto.UpdateProfileResponse;
import com.dorandoran.dorandoran.core.user.domain.User;

public interface ProfileService {

    void createProfile(User user);

    UpdateProfileResponse updateProfile(SecurityUser securityUser, UpdateProfileRequest request);
}
