package com.dorandoran.dorandoran.global.error.exception;

import java.io.IOException;

public class DoranDoranIOException extends DoranDoranRuntimeException {

    private static final String MESSAGE_KEY = "error.ioexception";

    public DoranDoranIOException(String detailKey, Object... params) {
        super(MESSAGE_KEY + "." + detailKey, params);
    }
}
