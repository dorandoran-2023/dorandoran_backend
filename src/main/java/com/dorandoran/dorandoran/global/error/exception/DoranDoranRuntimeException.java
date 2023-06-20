package com.dorandoran.dorandoran.global.error.exception;

/**
 * Root RuntimeException for this Application
 */
public abstract class DoranDoranRuntimeException extends RuntimeException {
	private final String messageKey;
	private final Object[] params;

	public DoranDoranRuntimeException(String messageKey, Object[] params) {
		this.messageKey = messageKey;
		this.params = params;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public Object[] getParams() {
		return params;
	}
}
