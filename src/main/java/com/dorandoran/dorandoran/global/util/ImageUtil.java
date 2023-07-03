package com.dorandoran.dorandoran.global.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collection;

import org.springframework.web.multipart.MultipartFile;

import com.dorandoran.dorandoran.core.image.domain.ImageType;
import com.dorandoran.dorandoran.global.error.exception.ImageUploadException;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class ImageUtil {

    public static ByteArrayInputStream toInputStream(MultipartFile file) {

        ByteArrayInputStream inputStream;
        try {
            byte[] byteArray = file.getBytes();
            inputStream = new ByteArrayInputStream(byteArray);
        } catch (IOException e) {
            throw new ImageUploadException("convert", file.getOriginalFilename());
        }
        return inputStream;
    }

    public static void checkExtension(String value, Collection<String> collection) {
        if (!collection.contains(value)) {
            throw new ImageUploadException("extension.unsupported", value);
        }
    }

    public static ImageType checkAndGetImageType(String value) {
        ImageType imageType;

        try {
            imageType = ImageType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ImageUploadException("type.notfound", value);
        }

        return imageType;
    }
}
