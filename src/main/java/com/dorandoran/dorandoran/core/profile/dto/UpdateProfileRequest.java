package com.dorandoran.dorandoran.core.profile.dto;

import static com.dorandoran.dorandoran.core.image.domain.ImageType.*;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.dorandoran.dorandoran.core.image.dto.UploadImageDTO;
import com.dorandoran.dorandoran.core.profile.domain.Nickname;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateProfileRequest {
	private Nickname nickname;
	private String bio;
	private Optional<UploadImageDTO> profileImage;
	private Optional<UploadImageDTO> backgroundImage;

	public UpdateProfileRequest(Nickname nickname, String bio, MultipartFile profileImage,
		MultipartFile backgroundImage) {
		this.nickname = nickname;
		this.bio = bio;
		this.profileImage = Optional.ofNullable(profileImage).map(file -> new UploadImageDTO(file, PROFILE));
		this.backgroundImage = Optional.ofNullable(backgroundImage).map(file -> new UploadImageDTO(file, BACKGROUND));
	}
}
