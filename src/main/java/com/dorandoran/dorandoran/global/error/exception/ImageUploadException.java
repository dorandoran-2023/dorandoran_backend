package com.dorandoran.dorandoran.global.error.exception;

public class ImageUploadException extends DoranDoranIOException {

    protected static final String MESSAGE_KEY = "image-upload";

    public ImageUploadException(String detailKey, Object... params) {
        super(MESSAGE_KEY + "." + detailKey, params);
    }
}
