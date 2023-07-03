package com.dorandoran.dorandoran.core.profile.dto;

import static com.dorandoran.dorandoran.core.image.domain.ImageType.BACKGROUND;
import static com.dorandoran.dorandoran.core.image.domain.ImageType.PROFILE;

import java.util.Map;

import com.dorandoran.dorandoran.core.image.domain.ImageType;
import com.dorandoran.dorandoran.core.profile.domain.Profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileResponse {

    private String nickname;
    private String bio;
    private String profileImageUrl;
    private String backgroundImageUrl;

    public static UpdateProfileResponse ofEntity(Profile profile, Map<ImageType, String> images) {

        return new UpdateProfileResponse(
                profile.getNickname().getValue(),
                profile.getBio(),
                images.get(PROFILE),
                images.get(BACKGROUND));
    }
}
