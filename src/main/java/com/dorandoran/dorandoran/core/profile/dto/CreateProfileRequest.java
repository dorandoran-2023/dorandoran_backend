package com.dorandoran.dorandoran.core.profile.dto;

import com.dorandoran.dorandoran.core.image.domain.BackgroundImage;
import com.dorandoran.dorandoran.core.image.domain.Image;
import com.dorandoran.dorandoran.core.image.domain.ProfileImage;
import com.dorandoran.dorandoran.core.profile.domain.Nickname;
import com.dorandoran.dorandoran.core.user.domain.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProfileRequest {
    private Email email;
    private Nickname nickname;
    private String bio;
    private ProfileImage profileImage;
    private BackgroundImage backgroundImage;
}
