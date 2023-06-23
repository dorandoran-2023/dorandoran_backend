package com.dorandoran.dorandoran.infra.S3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

import com.dorandoran.dorandoran.config.S3.S3Properties;
import com.dorandoran.dorandoran.core.image.application.ImageService;
import com.dorandoran.dorandoran.core.image.dto.UploadImageDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class S3ImageService implements ImageService {

    private final AmazonS3 amazonS3;
    private final S3Properties s3Properties;

    @Override
    public String upload(UploadImageDTO image) {

        String objectKey = image.getImageType().toString() + "/" + image.getFilename();

        PutObjectRequest putRequest = new PutObjectRequest(s3Properties.getBucket(), objectKey, image.getInputStream(), null)
                .withCannedAcl(CannedAccessControlList.PublicRead);
        amazonS3.putObject(putRequest);

        return amazonS3.getUrl(s3Properties.getBucket(), objectKey).toString();
    }

    @Override
    public void delete(String imageName, String imageType) {

        String objectKey = imageType + "/" + imageName;

        DeleteObjectRequest deleteRequest = new DeleteObjectRequest(s3Properties.getBucket(), objectKey);
        amazonS3.deleteObject(deleteRequest);
    }
}
