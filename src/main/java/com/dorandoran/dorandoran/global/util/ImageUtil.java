package com.dorandoran.dorandoran.global.util;

import com.dorandoran.dorandoran.core.image.domain.ImageType;
import com.dorandoran.dorandoran.global.error.exception.ImageUploadException;

import java.util.Collection;

public class ImageUtil {

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
