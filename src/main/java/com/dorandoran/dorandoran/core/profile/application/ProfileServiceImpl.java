package com.dorandoran.dorandoran.core.profile.application;

import static com.dorandoran.dorandoran.core.image.domain.ImageType.BACKGROUND;
import static com.dorandoran.dorandoran.core.image.domain.ImageType.PROFILE;

import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dorandoran.dorandoran.core.common.SecurityUser;
import com.dorandoran.dorandoran.core.image.application.ImageService;
import com.dorandoran.dorandoran.core.image.domain.BackgroundImage;
import com.dorandoran.dorandoran.core.image.domain.ImageType;
import com.dorandoran.dorandoran.core.image.domain.ProfileImage;
import com.dorandoran.dorandoran.core.image.dto.UploadImageDTO;
import com.dorandoran.dorandoran.core.profile.domain.Nickname;
import com.dorandoran.dorandoran.core.profile.domain.Profile;
import com.dorandoran.dorandoran.core.profile.dto.UpdateProfileRequest;
import com.dorandoran.dorandoran.core.profile.dto.UpdateProfileResponse;
import com.dorandoran.dorandoran.core.profile.repository.ProfileRepository;
import com.dorandoran.dorandoran.global.error.exception.BadRequestException;
import com.dorandoran.dorandoran.global.error.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ImageService imageService;

    @Override
    @Transactional
    public UpdateProfileResponse updateProfile(SecurityUser securityUser, UpdateProfileRequest request) {

        // get User from securityUser
        long profileId = securityUser.getProfileId();
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new NotFoundException("profile", profileId));

        // update profile
        Nickname nickname = request.getNickname();
        String bio = request.getBio();
        if (!profileRepository.existsByNickname(nickname)) {
            throw new BadRequestException("update-profile-failed.nickname.duplicated", nickname);
        }
        profile.update(nickname, bio);

        // update and set images
        Map<ImageType, String> imageUrls = uploadImages(profile, request);
        imageService.updateImages(profile, imageUrls);

        return UpdateProfileResponse.ofEntity(profile, imageUrls);
    }

    private Map<ImageType, String> uploadImages(Profile profile, UpdateProfileRequest request) {

        Optional<UploadImageDTO> profileImage = request.getProfileImage();
        Optional<UploadImageDTO> backgroundImage = request.getBackgroundImage();

        Map<ImageType, String> imageUrls = new HashMap<>();

        profileImage.ifPresentOrElse(
                image -> {
                    image.setFilename(String.valueOf(profile.getId()));
                    imageUrls.put(PROFILE, imageService.upload(image));
                },
                () -> {
                    imageUrls.put(PROFILE, ProfileImage.DEFAULT_PROFILE_IMAGE);
                });

        backgroundImage.ifPresentOrElse(
                image -> {
                    image.setFilename(String.valueOf(profile.getId()));
                    imageUrls.put(BACKGROUND, imageService.upload(image));
                },
                () -> {
                    imageUrls.put(BACKGROUND, BackgroundImage.DEFAULT_BACKGROUND_IMAGE);
                });

        return imageUrls;
    }
}
