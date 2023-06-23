package com.dorandoran.dorandoran.controller;

import com.dorandoran.dorandoran.core.common.SecurityUser;
import com.dorandoran.dorandoran.core.profile.application.ProfileService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dorandoran.dorandoran.core.profile.dto.CreateProfileRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("v1/profiles")
    public void createProfile(@AuthenticationPrincipal SecurityUser securityUser, CreateProfileRequest request) {

        profileService.createProfile(securityUser, request);
    }
}
