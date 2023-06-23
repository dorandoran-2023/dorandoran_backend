package com.dorandoran.dorandoran.core.profile.dto;

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
}
