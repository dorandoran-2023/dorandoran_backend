package com.dorandoran.dorandoran.global.util;

import java.util.regex.Pattern;

import org.springframework.data.redis.serializer.SerializationException;

import com.dorandoran.dorandoran.global.error.exception.InputValidationException;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class Preconditions {
	public static void checkNotNull(Object value, String message) {
		if (value == null) {
			throw new NullPointerException(message);
		}
	}

	public static void checkNotNullForSerializer(Object target, String name) {
		if (target == null) {
			throw new SerializationException(name + "cannot be null");
		}
	}

	public static void checkPatternMatches(Pattern pattern, String value) {
		if (!pattern.matcher(value).find()) {
			throw new InputValidationException("pattern", "email");
		}
	}

	public static void checkLength(int minLength, int maxLength, String value, String type) {
		if (value.length() < minLength || value.length() > maxLength) {
			throw new InputValidationException("length", type, minLength, maxLength);
		}
	}
}
