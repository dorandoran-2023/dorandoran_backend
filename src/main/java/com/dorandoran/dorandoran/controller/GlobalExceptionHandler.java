package com.dorandoran.dorandoran.controller;

import static org.springframework.http.HttpStatus.*;

import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.dorandoran.dorandoran.global.error.ErrorResponse;
import com.dorandoran.dorandoran.global.error.exception.DoranDoranRuntimeException;
import com.dorandoran.dorandoran.global.error.exception.DoranDoranIOException;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	private final MessageSourceAccessor messageSourceAccessor;

	public GlobalExceptionHandler(MessageSource messageSource) {
		this.messageSourceAccessor = new MessageSourceAccessor(messageSource);
	}

	@ResponseStatus(INTERNAL_SERVER_ERROR)
	@ExceptionHandler(DoranDoranIOException.class)
	public ErrorResponse handleDoranDoranIOException(DoranDoranIOException exception) {
		String type = exception.getClass().getSimpleName();
		String message = messageSourceAccessor.getMessage(exception.getMessageKey(), exception.getParams());
		return new ErrorResponse(INTERNAL_SERVER_ERROR, type, message);
	}

	@ResponseStatus(INTERNAL_SERVER_ERROR)
	@ExceptionHandler(IOException.class)
	public ErrorResponse handleIOException(Exception exception) {
		log.info(exception.getMessage(), exception);
		String type = exception.getClass().getSimpleName();
		String message = exception.getMessage();
		return new ErrorResponse(INTERNAL_SERVER_ERROR, type, message);
	}

	@ResponseStatus(BAD_REQUEST)
	@ExceptionHandler(DoranDoranRuntimeException.class)
	public ErrorResponse handleDoranDoranRuntimeException(DoranDoranRuntimeException exception) {
		String type = exception.getClass().getSimpleName();
		String message = messageSourceAccessor.getMessage(exception.getMessageKey(), exception.getParams());
		return new ErrorResponse(BAD_REQUEST, type, message);
	}

	@ResponseStatus(BAD_REQUEST)
	@ExceptionHandler(RuntimeException.class)
	public ErrorResponse handleRuntimeException(Exception exception) {
		log.info(exception.getMessage(), exception);
		String type = exception.getClass().getSimpleName();
		String message = exception.getMessage();
		return new ErrorResponse(BAD_REQUEST, type, message);
	}

	@ResponseStatus(NOT_FOUND)
	@ExceptionHandler(NoHandlerFoundException.class)
	public ErrorResponse handleNoHandlerFoundException(Exception exception) {
		String type = exception.getClass().getSimpleName();
		String message = exception.getMessage();
		return new ErrorResponse(NOT_FOUND, type, message);
	}

	@ResponseStatus(INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ErrorResponse handleException(Exception exception) {
		log.error(exception.getMessage(), exception);
		String type = exception.getClass().getSimpleName();
		String message = exception.getMessage();
		return new ErrorResponse(INTERNAL_SERVER_ERROR, type, message);
	}
}
