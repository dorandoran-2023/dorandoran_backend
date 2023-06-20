package com.dorandoran.dorandoran.global.error.exception;

public class BadRequestException extends DoranDoranRuntimeException {
	protected static final String MESSAGE_KEY = "error.bad-request";

	public BadRequestException(String detailKey, Object... params) {
		super(MESSAGE_KEY + "." + detailKey, params);
	}
}
