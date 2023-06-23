package com.dorandoran.dorandoran.core.profile.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dorandoran.dorandoran.core.common.SecurityUser;
import com.dorandoran.dorandoran.core.image.application.ImageService;
import com.dorandoran.dorandoran.core.image.dto.UploadImageDTO;
import com.dorandoran.dorandoran.core.profile.domain.Profile;
import com.dorandoran.dorandoran.core.profile.dto.CreateProfileRequest;
import com.dorandoran.dorandoran.core.profile.dto.CreateProfileResponse;
import com.dorandoran.dorandoran.core.profile.repository.ProfileRepository;
import com.dorandoran.dorandoran.core.user.application.UserService;
import com.dorandoran.dorandoran.core.user.domain.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ImageService imageService;
    private final UserService userService;

    @Override
    @Transactional
    public CreateProfileResponse createProfile(SecurityUser securityUser, CreateProfileRequest request) {

        // get User from securityUser
        // TODO: getUserById method
        User user = userService.getUserById(securityUser.getUserId());

        // save profile and set images
        Profile profile = new Profile(user, request.getNickname(), request.getBio());
        profileRepository.save(profile);
        List<String> images = setImages(profile, request);

        // return response
        return CreateProfileResponse.ofEntity(profile, images);
    }

    private List<String> setImages(Profile profile, CreateProfileRequest request) {

        UploadImageDTO profileImage = request.getProfileImage();
        UploadImageDTO backgroundImage = request.getBackgroundImage();

        profileImage.setFilename(String.valueOf(profile.getId()));
        backgroundImage.setFilename(String.valueOf(profile.getId()));

        return List.of(imageService.upload(profileImage), imageService.upload(backgroundImage));
    }
}
