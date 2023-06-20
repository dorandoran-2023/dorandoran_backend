package com.dorandoran.dorandoran.global.error;

import org.springframework.http.HttpStatus;

import lombok.Data;

/**
 * response format for exception
 */
@Data
public class ErrorResponse {
	private final int code;
	private final String type;
	private final String message;

	public ErrorResponse(HttpStatus httpStatus, String type, String message) {
		this.code = httpStatus.value();
		this.type = type;
		this.message = message;
	}
}
