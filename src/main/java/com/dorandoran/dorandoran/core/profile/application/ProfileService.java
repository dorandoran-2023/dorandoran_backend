package com.dorandoran.dorandoran.core.profile.application;

import com.dorandoran.dorandoran.core.common.SecurityUser;
import com.dorandoran.dorandoran.core.profile.dto.UpdateProfileRequest;
import com.dorandoran.dorandoran.core.profile.dto.UpdateProfileResponse;

public interface ProfileService {

    public UpdateProfileResponse updateProfile(SecurityUser securityUser, UpdateProfileRequest request);
}
