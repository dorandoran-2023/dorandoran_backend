package com.dorandoran.dorandoran.core.profile.application;

import com.dorandoran.dorandoran.core.common.SecurityUser;
import com.dorandoran.dorandoran.core.profile.dto.CreateProfileRequest;
import com.dorandoran.dorandoran.core.profile.dto.CreateProfileResponse;

public interface ProfileService {

    public CreateProfileResponse createProfile(SecurityUser securityUser, CreateProfileRequest request);
}
