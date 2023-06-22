package com.dorandoran.dorandoran.core.user.domain;

import static com.dorandoran.dorandoran.global.util.Preconditions.*;
import static lombok.AccessLevel.*;

import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PROTECTED)
@EqualsAndHashCode
@Embeddable
public class Email {
	/* EMAIL_PATTERN - RFC 5322 */
	private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");

	@Column(name = "email", unique = true, nullable = false)
	private String value;

	public Email(String value) {
		checkPatternMatches(EMAIL_PATTERN, value);
		this.value = value;
	}

	public static Email of(String value) {
		return new Email(value);
	}
}
