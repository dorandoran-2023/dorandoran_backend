package com.dorandoran.dorandoran.global.error.exception;

public class ImageUploadException extends BadRequestException {

    private static final String MESSAGE_KEY = "image-upload";

    public ImageUploadException(String detailKey, Object... params) {
        super(detailKey + "." + detailKey, params);
    }
}
