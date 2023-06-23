package com.dorandoran.dorandoran.core.profile.dto;

import java.util.List;

import com.dorandoran.dorandoran.core.profile.domain.Profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProfileResponse {

    private String nickname;
    private String bio;
    private String profileImageUrl;
    private String backgroundImageUrl;

    public static CreateProfileResponse ofEntity(Profile profile, List<String> images) {
        return new CreateProfileResponse(
                profile.getNickname().getValue(),
                profile.getBio(),
                images.get(0),
                images.get(1));
    }
}
