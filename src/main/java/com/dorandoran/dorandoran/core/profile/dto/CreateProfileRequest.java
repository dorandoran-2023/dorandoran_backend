package com.dorandoran.dorandoran.core.profile.dto;

import static com.dorandoran.dorandoran.core.image.domain.ImageType.*;

import org.springframework.web.multipart.MultipartFile;

import com.dorandoran.dorandoran.core.image.dto.UploadImageDTO;
import com.dorandoran.dorandoran.core.profile.domain.Nickname;
import com.dorandoran.dorandoran.core.user.domain.Email;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateProfileRequest {
    private Email email;
    private Nickname nickname;
    private String bio;
    private UploadImageDTO profileImage;
    private UploadImageDTO backgroundImage;

    public CreateProfileRequest(Email email, Nickname nickname, String bio, MultipartFile profileImage, MultipartFile backgroundImage) {
        this.email = email;
        this.nickname = nickname;
        this.bio = bio;
        this.profileImage = new UploadImageDTO(profileImage, PROFILE);
        this.backgroundImage = new UploadImageDTO(backgroundImage, BACKGROUND);
    }
}
