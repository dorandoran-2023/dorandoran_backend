package com.dorandoran.dorandoran.core.image.application;

import static com.dorandoran.dorandoran.core.image.domain.ImageType.BACKGROUND;
import static com.dorandoran.dorandoran.core.image.domain.ImageType.PROFILE;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.dorandoran.dorandoran.core.image.domain.BackgroundImage;
import com.dorandoran.dorandoran.core.image.domain.Image;
import com.dorandoran.dorandoran.core.image.domain.ImageType;
import com.dorandoran.dorandoran.core.image.domain.ProfileImage;
import com.dorandoran.dorandoran.core.image.dto.UploadImageDTO;
import com.dorandoran.dorandoran.core.image.repository.ImageRepository;
import com.dorandoran.dorandoran.core.profile.domain.Profile;
import com.dorandoran.dorandoran.infra.S3.S3ImageService;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final S3ImageService s3ImageService;

    @Override
    public String upload(UploadImageDTO image) {
        return s3ImageService.upload(image);
    }

    @Override
    public void delete(String imageName, String imageType) {
        s3ImageService.delete(imageName, imageType);
    }

    @Override
    @Transactional
    public void updateImages(Profile profile, Map<ImageType, String> imageUrls) {
        for (Image image : imageRepository.findByProfile_Id(profile.getId())) {
            if (image instanceof ProfileImage) {
                image.update(imageUrls.get(PROFILE));
            } else if (image instanceof BackgroundImage) {
                image.update(imageUrls.get(BACKGROUND));
            }
        }
    }
}
