package com.dorandoran.dorandoran.core.image.application;

import com.dorandoran.dorandoran.core.image.domain.ImageType;
import com.dorandoran.dorandoran.core.image.dto.UploadImageDTO;
import com.dorandoran.dorandoran.core.profile.domain.Profile;

import java.util.Map;

public interface ImageService {

    String upload(UploadImageDTO image);

    void delete(String imageName, String imageType);

    void updateImages(Profile profile, Map<ImageType, String> imageUrls);
}
